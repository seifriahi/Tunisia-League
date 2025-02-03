package com.actia.tunisialeague.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.actia.tunisialeague.R
import com.actia.tunisialeague.utils.*
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    lateinit var txtEmailLayout: TextInputLayout
    lateinit var txtPasswordLayout: TextInputLayout
    lateinit var txtEmail: TextInputEditText
    lateinit var txtPwd: TextInputEditText
    lateinit var cbRememberMe: CheckBox
    lateinit var btnLogin: Button
    lateinit var tvSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtEmailLayout = findViewById(R.id.txtEmailLayout)
        txtPasswordLayout = findViewById(R.id.txtPasswordLayout)
        txtEmail = findViewById(R.id.txtEmail)
        txtPwd = findViewById(R.id.txtPwd)
        cbRememberMe = findViewById(R.id.cbRememberMe)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignUp = findViewById(R.id.tvSignUp)

        txtEmail.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateEmail()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })

        txtPwd.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePassword()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })

        btnLogin.setOnClickListener {

            MyStatics.hideKeyboard(this, btnLogin)

            if (validateEmail() && validatePassword()){

                MyDataBase.getInstance(this).userDao().getUserByEmailAndPassword(txtEmail.text.toString(), txtPwd.text.toString()).also {
                    if (it == null){
                        Snackbar.make(findViewById(R.id.container), getString(R.string.userNotFound),
                            Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(getColor(R.color.black))
                            .show()
                    }else{
                        if (cbRememberMe.isChecked){
                            getSharedPreferences(PREF_FILE_LOGIN, MODE_PRIVATE).edit().apply{
                                putInt(ID_USER, it.id)
                                putString(FIRST_NAME, it.firstName)
                                putString(LAST_NAME, it.lastName)
                                putString(EMAIL, it.email)
                                putString(PASSWORD, it.password)
                                putBoolean(IS_REMEMBERED, cbRememberMe.isChecked)
                            }.apply()
                        }
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    }
                }
            }
        }

        tvSignUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            finish()
        }

    }

    private fun validateEmail(): Boolean {
        txtEmailLayout.isErrorEnabled = false

        if (txtEmail.text!!.isEmpty()) {
            txtEmailLayout.error = getString(R.string.mustNotBeEmpty)
            txtEmail.requestFocus()
            return false
        }else{
            txtEmailLayout.isErrorEnabled = false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail.text.toString()).matches()) {
            txtEmailLayout.error = getString(R.string.checkYourEmail)
            txtEmail.requestFocus()
            return false
        }else{
            txtEmailLayout.isErrorEnabled = false
        }

        return true
    }

    private fun validatePassword(): Boolean {
        txtPasswordLayout.isErrorEnabled = false

        if (txtPwd.text!!.isEmpty()) {
            txtPasswordLayout.error = getString(R.string.mustNotBeEmpty)
            txtPwd.requestFocus()
            return false
        }else{
            txtPasswordLayout.isErrorEnabled = false
        }

        if (txtPwd.text!!.length < 6) {
            txtPasswordLayout.error = getString(R.string.checkYourPwd)
            txtPwd.requestFocus()
            return false
        }else{
            txtPasswordLayout.isErrorEnabled = false
        }

        return true
    }
}
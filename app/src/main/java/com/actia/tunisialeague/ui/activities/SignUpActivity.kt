package com.actia.tunisialeague.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.actia.tunisialeague.R
import com.actia.tunisialeague.models.User
import com.actia.tunisialeague.utils.MyDataBase
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {

    lateinit var txtFirstNameLayout: TextInputLayout
    lateinit var txtLastNameLayout: TextInputLayout
    lateinit var txtEmailLayout: TextInputLayout
    lateinit var txtPasswordLayout: TextInputLayout
    lateinit var txtFirstName: TextInputEditText
    lateinit var txtLastName: TextInputEditText
    lateinit var txtEmail: TextInputEditText
    lateinit var txtPwd: TextInputEditText
    lateinit var btnSubmit: Button
    lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        txtFirstNameLayout = findViewById(R.id.txtFirstNameLayout)
        txtLastNameLayout = findViewById(R.id.txtLastNameLayout)
        txtEmailLayout = findViewById(R.id.txtEmailLayout)
        txtPasswordLayout = findViewById(R.id.txtPasswordLayout)
        txtFirstName = findViewById(R.id.txtFirstName)
        txtLastName = findViewById(R.id.txtLastName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPwd = findViewById(R.id.txtPwd)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvLogin = findViewById(R.id.tvLogin)

        txtFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateFirstName()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        txtLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateLastName()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        txtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateEmail()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        txtPwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePassword()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        btnSubmit.setOnClickListener {
            if (validateFirstName() && validateLastName() && validateEmail() && validatePassword()){

                val user = User(0, txtFirstName.text.toString(), txtLastName.text.toString(), txtEmail.text.toString(), txtPwd.text.toString())

                MyDataBase.getInstance(this).userDao().getUserByEmailAndPassword(user.email, user.password).also {
                    if (it == null){
                        MyDataBase.getInstance(this).userDao().insert(user)
                        startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                        finish()
                    }else{
                        Snackbar.make(findViewById(R.id.container), getString(R.string.userExist),
                            Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(getColor(R.color.black))
                            .show()
                    }
                }

            }
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }

    }

    private fun validateFirstName(): Boolean {
        txtFirstNameLayout.isErrorEnabled = false

        if (txtFirstName.text!!.isEmpty()) {
            txtFirstNameLayout.error = getString(R.string.mustNotBeEmpty)
            txtFirstName.requestFocus()
            return false
        }

        if (txtFirstName.text!!.length < 3) {
            txtFirstNameLayout.error = getString(R.string.checkName)
            txtFirstName.requestFocus()
            return false
        }

        return true
    }

    private fun validateLastName(): Boolean {
        txtLastNameLayout.isErrorEnabled = false

        if (txtLastName.text!!.isEmpty()) {
            txtLastNameLayout.error = getString(R.string.mustNotBeEmpty)
            txtFirstName.requestFocus()
            return false
        }

        if (txtLastName.text!!.length < 3) {
            txtLastNameLayout.error = getString(R.string.checkName)
            txtLastName.requestFocus()
            return false
        }

        return true
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
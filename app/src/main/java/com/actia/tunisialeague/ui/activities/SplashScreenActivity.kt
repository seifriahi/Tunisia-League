package com.actia.tunisialeague.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.actia.tunisialeague.R
import com.actia.tunisialeague.utils.IS_REMEMBERED
import com.actia.tunisialeague.utils.PREF_FILE_LOGIN

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            var intent = Intent(this, LoginActivity::class.java)

            if (getSharedPreferences(PREF_FILE_LOGIN, MODE_PRIVATE).getBoolean(IS_REMEMBERED, false)){
                intent = Intent(this, HomeActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 3000)
    }
}
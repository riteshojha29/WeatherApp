package com.ritesh.weatherapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ritesh.weatherapp.ui.MainActivity
import com.ritesh.weatherapp.R

class SplashActivity: AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 3000 // 3 secs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
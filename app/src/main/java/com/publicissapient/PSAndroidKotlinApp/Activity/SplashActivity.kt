package com.publicissapient.PSAndroidKotlinApp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.publicissapient.PSAndroidKotlinApp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity( Intent (this, MainActivity::class.java))
        finish()
    }
}
package com.binarproject.tmdb.mains

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.binarproject.tmdb.R

class ActivitySplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed(Runnable {
            var main = Intent(this, ActivityMainScreen::class.java)
            startActivity(main)
            finish()
        }, 1000)
    }
}
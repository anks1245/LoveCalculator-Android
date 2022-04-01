package com.app.lovecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast

class SplashScreen : AppCompatActivity() {
    private lateinit var appName:TextView
    private lateinit var tagLine:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        appName = findViewById(R.id.app_name)
        tagLine = findViewById(R.id.tagline)

        val anim = AnimationUtils.loadAnimation(this,R.anim.fade)

        appName.startAnimation(anim)
        tagLine.startAnimation(anim)

        Handler().postDelayed({
//            Toast.makeText(this, "Start MainActivity", Toast.LENGTH_SHORT).show()
                              startActivity(Intent(this,MainActivity::class.java))
            finish()
        },4500)

    }
}
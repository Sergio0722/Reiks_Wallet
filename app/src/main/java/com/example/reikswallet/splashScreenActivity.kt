package com.example.reikswallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class splashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val backgroundimg: ImageView = findViewById(R.id.iv_logo)

        val sideAnimation = AnimationUtils.loadAnimation(this,R.anim.shde)
        backgroundimg.startAnimation(sideAnimation)

        Handler().postDelayed({

            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }, 3000)





    }

}
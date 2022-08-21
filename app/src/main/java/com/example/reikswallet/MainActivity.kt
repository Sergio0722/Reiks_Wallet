package com.example.reikswallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnRegistroEmail = findViewById<Button>(R.id.registro_email)
        btnRegistroEmail.setOnClickListener(abrirRegistros)

    }
    val abrirRegistros = View.OnClickListener { view ->
        when(view.getId()) {
            R.id.registro_email -> abrirEmail()

        }

    }
    fun abrirEmail(){
        val intent = Intent(this, MainRegistroActivity::class.java)
        startActivity(intent)

    }






    }


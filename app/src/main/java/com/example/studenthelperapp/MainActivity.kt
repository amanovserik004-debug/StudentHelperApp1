package com.example.studenthelperapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser == null) {
            // Егер пользователь жоқ болса, AuthActivity-ға қайтамыз
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // UI элементтерін іздеу
        val tvWelcome = findViewById<TextView>(R.id.tv_welcome)
        val btnLogout = findViewById<Button>(R.id.btn_logout)

        // Пользователь ақпаратын көрсету
        tvWelcome.text = "Добро пожаловать, ${currentUser.email ?: "Пользователь"}!"

        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }

        println("DEBUG: MainActivity loaded successfully")
    }
}
package com.example.studenthelperapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studenthelperapp.databinding.ActivityMainMenuBinding
import com.google.firebase.auth.FirebaseAuth

class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser == null) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return
        }

        binding.tvWelcome.text = "Добро пожаловать, ${currentUser.email}"

        binding.cardSchedule.setOnClickListener {
            startActivity(Intent(this, ScheduleActivity::class.java))
        }

        binding.cardReminders.setOnClickListener {
            startActivity(Intent(this, ReminderActivity::class.java))
        }

        binding.cardGpa.setOnClickListener {
            startActivity(Intent(this, GpaCalculatorActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}

package com.example.studenthelperapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studenthelperapp.databinding.ActivityReminderBinding

class ReminderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReminderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvReminderInfo.text = "Здесь будут ваши напоминания о заданиях 📚"
    }
}

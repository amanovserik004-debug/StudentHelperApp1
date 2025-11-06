package com.example.studenthelperapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studenthelperapp.databinding.ActivityGpaCalculatorBinding

class GpaCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGpaCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGpaCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvGpaInfo.text = "Здесь будет калькулятор GPA 🎓"
    }
}

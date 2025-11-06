package com.example.studenthelperapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.studenthelperapp.databinding.ActivityAuthBinding
import com.example.studenthelperapp.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()
    private var isLoginMode: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Тікелей Firebase арқылы тексеру
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            navigateToMainActivity()
            return
        }

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // DEBUG: Байланыс дұрыс орнағанын тексеру
        println("DEBUG: AuthActivity layout loaded")

        setupListeners()
        setupObservers()
        updateUiForMode()
    }

    private fun setupListeners() {
        binding.btnAuth.setOnClickListener {
            performAuth()
        }

        binding.tvSwitchMode.setOnClickListener {
            isLoginMode = !isLoginMode
            updateUiForMode()
        }
    }

    private fun setupObservers() {
        authViewModel.user.observe(this) { user ->
            if (user != null) {
                Toast.makeText(this, "Успешный вход: ${user.email}", Toast.LENGTH_SHORT).show()
                navigateToMainActivity()
            }
        }

        authViewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnAuth.isEnabled = !isLoading
        }

        authViewModel.error.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(this, "Ошибка: $errorMessage", Toast.LENGTH_LONG).show()
                authViewModel.clearError()
            }
        }
    }

    private fun performAuth() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Введите Email и пароль", Toast.LENGTH_SHORT).show()
            return
        }

        if (isLoginMode) {
            authViewModel.login(email, password)
        } else {
            authViewModel.register(email, password)
        }
    }

    private fun updateUiForMode() {
        if (isLoginMode) {
            binding.tvTitle.text = "Вход"
            binding.btnAuth.text = "Войти"
            binding.tvSwitchMode.text = "Нет аккаунта? Зарегистрироваться"
        } else {
            binding.tvTitle.text = "Регистрация"
            binding.btnAuth.text = "Зарегистрироваться"
            binding.tvSwitchMode.text = "Уже есть аккаунт? Войти"
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainMenuActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }
}
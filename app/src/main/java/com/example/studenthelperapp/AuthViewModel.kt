package com.example.studenthelperapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthViewModel : ViewModel() {

    // Firebase Authentication instance
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // LiveData для отслеживания состояния аутентификации
    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    // LiveData для отслеживания ошибок
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    // LiveData для отслеживания состояния загрузки
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        // БУЛ ЖЕРДЕ МӘСЕЛЕ БОЛУЫ МҮМКІН - Firebase баптауларын тексеру керек
        _user.value = auth.currentUser
        println("AuthViewModel init: currentUser = ${auth.currentUser}")
    }

    fun clearError() {
        _error.value = null
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        _error.value = null

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    _user.value = auth.currentUser
                    println("Login successful: ${auth.currentUser?.email}")
                } else {
                    _error.value = task.exception?.message ?: "Ошибка входа"
                    println("Login error: ${task.exception?.message}")
                }
            }
    }

    fun register(email: String, password: String) {
        _isLoading.value = true
        _error.value = null

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    _user.value = auth.currentUser
                    println("Registration successful: ${auth.currentUser?.email}")
                } else {
                    _error.value = task.exception?.message ?: "Ошибка регистрации"
                    println("Registration error: ${task.exception?.message}")
                }
            }
    }
}
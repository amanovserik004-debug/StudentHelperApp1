package com.example.studenthelperapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.example.studenthelperapp.data.AppDatabase
import com.example.studenthelperapp.data.Lesson
import kotlinx.coroutines.launch

class ScheduleViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).lessonDao()
    val lessons: LiveData<List<Lesson>> = dao.getAllLessons()

    fun addLesson(lesson: Lesson) {
        viewModelScope.launch {
            dao.insertLesson(lesson)
        }
    }

    fun updateLesson(lesson: Lesson) {
        viewModelScope.launch {
            dao.updateLesson(lesson)
        }
    }

    fun deleteLesson(lesson: Lesson) {
        viewModelScope.launch {
            dao.deleteLesson(lesson)
        }
    }
}

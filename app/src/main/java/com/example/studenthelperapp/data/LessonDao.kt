package com.example.studenthelperapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LessonDao {
    @Query("SELECT * FROM lessons")
    fun getAllLessons(): LiveData<List<Lesson>>

    @Insert
    suspend fun insertLesson(lesson: Lesson)

    @Update
    suspend fun updateLesson(lesson: Lesson)

    @Delete
    suspend fun deleteLesson(lesson: Lesson)
}

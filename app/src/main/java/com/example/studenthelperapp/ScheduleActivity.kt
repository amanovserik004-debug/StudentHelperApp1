package com.example.studenthelperapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studenthelperapp.data.Lesson
import com.example.studenthelperapp.databinding.ActivityScheduleBinding
import com.example.studenthelperapp.viewmodel.ScheduleViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.widget.EditText

class ScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScheduleBinding
    private val viewModel: ScheduleViewModel by viewModels()
    private lateinit var adapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ScheduleAdapter(
            onDelete = { viewModel.deleteLesson(it) },
            onEdit = { showEditDialog(it) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.lessons.observe(this) { list ->
            adapter.submitList(list)
        }

        binding.fabAdd.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_lesson, null)
        val etSubject = dialogView.findViewById<EditText>(R.id.et_subject)
        val etTime = dialogView.findViewById<EditText>(R.id.et_time)

        MaterialAlertDialogBuilder(this)
            .setTitle("Добавить занятие")
            .setView(dialogView)
            .setPositiveButton("Сохранить") { _, _ ->
                val subject = etSubject.text.toString()
                val time = etTime.text.toString()
                if (subject.isNotEmpty() && time.isNotEmpty()) {
                    viewModel.addLesson(Lesson(0, subject, time))
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun showEditDialog(lesson: Lesson) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_lesson, null)
        val etSubject = dialogView.findViewById<EditText>(R.id.et_subject)
        val etTime = dialogView.findViewById<EditText>(R.id.et_time)

        etSubject.setText(lesson.subject)
        etTime.setText(lesson.time)

        MaterialAlertDialogBuilder(this)
            .setTitle("Изменить занятие")
            .setView(dialogView)
            .setPositiveButton("Сохранить") { _, _ ->
                val subject = etSubject.text.toString()
                val time = etTime.text.toString()
                if (subject.isNotEmpty() && time.isNotEmpty()) {
                    viewModel.updateLesson(lesson.copy(subject = subject, time = time))
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
}

package com.example.studenthelperapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studenthelperapp.data.Lesson

class ScheduleAdapter(
    private val onDelete: (Lesson) -> Unit,
    private val onEdit: (Lesson) -> Unit
) : ListAdapter<Lesson, ScheduleAdapter.LessonViewHolder>(DiffCallback()) {

    class LessonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSubject: TextView = view.findViewById(R.id.tv_subject)
        val tvTime: TextView = view.findViewById(R.id.tv_time)
        val btnDelete: ImageButton = view.findViewById(R.id.btn_delete)
        val btnEdit: ImageButton = view.findViewById(R.id.btn_edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = getItem(position)
        holder.tvSubject.text = lesson.subject
        holder.tvTime.text = lesson.time
        holder.btnDelete.setOnClickListener { onDelete(lesson) }
        holder.btnEdit.setOnClickListener { onEdit(lesson) }
    }

    class DiffCallback : DiffUtil.ItemCallback<Lesson>() {
        override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson) = oldItem == newItem
    }
}

package com.example.mviproject.mvi.m.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mviproject.R
import com.example.mviproject.mvi.m.Student

class AttendanceAdapter(private val students: List<Student>) :
    RecyclerView.Adapter<AttendanceAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvStudentName)
        val tvId: TextView = view.findViewById(R.id.tvStudentId)
        val ivAvatar: ImageView = view.findViewById(R.id.ivStudentAvatar)
        val btnHere: Button = view.findViewById(R.id.btnHere)
        val btnAway: Button = view.findViewById(R.id.btnAway)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_attendance, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.tvName.text = student.name
        holder.tvId.text = student.studentId
        holder.ivAvatar.setImageResource(student.avatar)

        // Reset UI state based on student data
        updateButtonStyles(holder, student)

        holder.btnHere.setOnClickListener {
            student.isPresent = true
            student.hasTakenAttendance = true
            updateButtonStyles(holder, student)
        }

        holder.btnAway.setOnClickListener {
            student.isPresent = false
            student.hasTakenAttendance = true
            updateButtonStyles(holder, student)
        }
    }

    private fun updateButtonStyles(holder: ViewHolder, student: Student) {
        if (!student.hasTakenAttendance) {
            // Default state
            holder.btnHere.alpha = 1.0f
            holder.btnAway.alpha = 1.0f
            return
        }

        if (student.isPresent) {
            holder.btnHere.alpha = 1.0f
            holder.btnAway.alpha = 0.3f // Dim the other button
        } else {
            holder.btnHere.alpha = 0.3f // Dim the other button
            holder.btnAway.alpha = 1.0f
        }
    }

    override fun getItemCount() = students.size
}
package com.example.mviproject.mvi.m.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mviproject.R

data class Student(
    val name: String,
    val id: String,
    val avatar: Int = R.drawable.img,  // ✅ already here!
    var isPresent: Boolean = false
)

class AttendanceAdapter(private val students: List<Student>) :
    RecyclerView.Adapter<AttendanceAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvStudentName)
        val tvId: TextView = view.findViewById(R.id.tvStudentId)
        val ivAvatar: ImageView = view.findViewById(R.id.ivStudentAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_attendance, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.tvName.text = student.name
        holder.tvId.text = student.id
        holder.ivAvatar.setImageResource(student.avatar)
    }

    override fun getItemCount() = students.size
}
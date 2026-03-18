package com.example.mviproject.mvi.m.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.mviproject.R

data class Student(
    val name: String,
    val id: String,
    val avatar: Int = R.drawable.img,
    var isPresent: Boolean = false
)

class AttendanceAdapter(private val students: List<Student>) :
    RecyclerView.Adapter<AttendanceAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView         = view.findViewById(R.id.tvStudentName)
        val tvId: TextView           = view.findViewById(R.id.tvStudentId)
        val ivAvatar: ImageView      = view.findViewById(R.id.ivStudentAvatar)
        val btnHere: AppCompatButton = view.findViewById(R.id.btnHere)
        val btnAway: AppCompatButton = view.findViewById(R.id.btnAway)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_attendance, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.tvName.text = student.name
        holder.tvId.text   = student.id
        holder.ivAvatar.setImageResource(student.avatar)

        // Reset both buttons visible
        holder.btnHere.visibility = View.VISIBLE
        holder.btnAway.visibility = View.VISIBLE

        // HERE — mark Present, hide AWAY
        holder.btnHere.setOnClickListener {
            student.isPresent = true
            holder.btnHere.visibility = View.VISIBLE
            holder.btnAway.visibility = View.GONE
        }

        // AWAY — mark Absent, hide HERE
        holder.btnAway.setOnClickListener {
            student.isPresent = false
            holder.btnAway.visibility = View.VISIBLE
            holder.btnHere.visibility = View.GONE
        }
    }

    override fun getItemCount() = students.size
}
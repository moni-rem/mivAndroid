package com.example.mviproject.mvi.m.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mviproject.R
import com.example.mviproject.mvi.m.Student

class StudentAdapter(
    private var students: MutableList<Student>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView   = itemView.findViewById(R.id.tvStudentName)
        val tvId: TextView     = itemView.findViewById(R.id.tvStudentId)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.tvName.text = student.name
        holder.tvId.text   = student.studentId

        if (student.isActive) {
            holder.tvStatus.text = "✓ Active"
            holder.tvStatus.setTextColor(Color.parseColor("#27913F"))
        } else {
            holder.tvStatus.text = "✗ Inactive"
            holder.tvStatus.setTextColor(Color.parseColor("#E74C3C"))
        }
    }

    override fun getItemCount(): Int = students.size

    fun updateList(newList: MutableList<Student>) {
        students = newList
        notifyDataSetChanged()
    }
}
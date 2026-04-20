package com.example.mviproject.mvi.m.ui

import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mviproject.R
import com.example.mviproject.mvi.m.Student

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val names = intent.getStringArrayListExtra("names") ?: arrayListOf()
        val ids = intent.getStringArrayListExtra("ids") ?: arrayListOf()
        val statuses = intent.getBooleanArrayExtra("statuses") ?: BooleanArray(0)
        val avatars = intent.getIntegerArrayListExtra("avatars") ?: arrayListOf()

        val studentList = names.mapIndexed { i, name ->
            Student(
                name = name,
                studentId = ids.getOrElse(i) { "" },
                isActive = true,
                avatar = avatars.getOrElse(i) { R.drawable.img },
                isPresent = statuses.getOrElse(i) { false }
            )
        }

        val total = studentList.size
        val present = studentList.count { it.isPresent }
        val absent = total - present
        val rate = if (total > 0) (present * 100 / total) else 0

        findViewById<TextView>(R.id.tvTotalCount).text = total.toString()
        findViewById<TextView>(R.id.tvPresentCount).text = present.toString()
        findViewById<TextView>(R.id.tvAbsentCount).text = absent.toString()
        findViewById<TextView>(R.id.tvAttendanceRate).text = "$rate%"

        val recyclerView = findViewById<RecyclerView>(R.id.rvReportStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ReportAdapter(studentList)

        findViewById<android.widget.Button>(R.id.btnBackRoster).setOnClickListener {
            val intent = Intent(this, SummaryActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    inner class ReportAdapter(private val students: List<Student>) :
        RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvReportStudentName)
            val tvStatus: TextView = view.findViewById(R.id.tvReportStatus)
            val ivAvatar: ImageView = view.findViewById(R.id.ivReportAvatar)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_report_student, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val student = students[position]
            holder.tvName.text = student.name
            holder.ivAvatar.setImageResource(student.avatar)
            if (student.isPresent) {
                holder.tvStatus.text = "Present"
                holder.tvStatus.setTextColor(0xFF4CAF50.toInt())
                holder.tvStatus.setBackgroundResource(R.drawable.bg_btn_here)
            } else {
                holder.tvStatus.text = "Absent"
                holder.tvStatus.setTextColor(0xFFF44336.toInt())
                holder.tvStatus.setBackgroundResource(R.drawable.bg_btn_away)
            }
        }

        override fun getItemCount() = students.size
    }
}
package com.example.mviproject.mvi.m.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mviproject.R
import com.example.mviproject.mvi.m.StudentRepository
import com.example.mviproject.mvi.m.adapter.AttendanceAdapter
import com.example.mviproject.mvi.m.Student
import com.google.android.material.bottomnavigation.BottomNavigationView

class AttendanceActivity : AppCompatActivity() {

    private lateinit var studentList: MutableList<Student>

    private fun goToReport() {
        val intent = Intent(this, ReportActivity::class.java)
        intent.putStringArrayListExtra("names", ArrayList(studentList.map { it.name }))
        intent.putStringArrayListExtra("ids", ArrayList(studentList.map { it.studentId }))
        intent.putExtra("statuses", studentList.map { it.isPresent }.toBooleanArray())
        
        val avatars = studentList.map { it.avatar }
        intent.putIntegerArrayListExtra("avatars", ArrayList(avatars))
        
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Directly use the repository list so changes are reflected globally
        studentList = StudentRepository.getStudents()

        val recyclerView = findViewById<RecyclerView>(R.id.rvStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AttendanceAdapter(studentList)

        findViewById<android.widget.Button>(R.id.btnSaveAttendance).setOnClickListener {
            goToReport()
        }
        findViewById<android.widget.Button>(R.id.btnGoBack).setOnClickListener {
            val intent = Intent(this, SummaryActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_attend
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_roster -> {
                    startActivity(Intent(this, SummaryActivity::class.java))
                    true
                }
                R.id.nav_attend -> true
                R.id.nav_add -> {
                    startActivity(Intent(this, AddStudentActivity::class.java))
                    true
                }
                R.id.nav_report -> { goToReport(); true }
                else -> false
            }
        }
    }
}
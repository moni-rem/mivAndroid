package com.example.mviproject.mvi.m.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mviproject.R
import com.example.mviproject.mvi.m.adapter.AttendanceAdapter
import com.example.mviproject.mvi.m.adapter.Student
import com.google.android.material.bottomnavigation.BottomNavigationView

class AttendanceActivity : AppCompatActivity() {

    private val studentList = mutableListOf(
        Student(name = "John Doe", id = "STU001", avatar = R.drawable.frog),
        Student(name = "Jane Smith", id = "STU002", avatar = R.drawable.hamburger),
        Student(name = "Bob Lee", id = "STU003", avatar = R.drawable.monkey),
        Student(name = "Alice", id = "STU004", avatar = R.drawable.monkey),
        Student(name = "Charlie", id = "STU005", avatar = R.drawable.monkey)
    )

    private fun goToReport() {
        val intent = Intent(this, ReportActivity::class.java)
        intent.putStringArrayListExtra("names", ArrayList(studentList.map { it.name }))
        intent.putStringArrayListExtra("ids", ArrayList(studentList.map { it.id }))
        intent.putExtra("statuses", studentList.map { it.isPresent }.toBooleanArray())
        intent.putIntegerArrayListExtra("avatars", ArrayList(studentList.map { it.avatar }))
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
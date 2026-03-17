package com.example.mviproject.mvi.m.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mviproject.R
import com.example.mviproject.mvi.m.Student
import com.example.mviproject.mvi.m.adapter.StudentAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class SummaryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private lateinit var etSearch: EditText

    private val allStudents = mutableListOf(
        Student("John Doe",   "STU001", true),
        Student("Jane Smith", "STU002", true),
        Student("Mike Lee",   "STU003", false),
        Student("Sarah Park", "STU004", true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        recyclerView  = findViewById(R.id.recyclerStudents)
        etSearch      = findViewById(R.id.etSearch)
        val btnAttend = findViewById<Button>(R.id.btnTakeAttendance)
        val btnAdd    = findViewById<Button>(R.id.btnAddStudent)

        // ── Student list ──
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(allStudents.toMutableList())
        recyclerView.adapter = adapter

        // ── Search filter ──
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val q = s.toString().lowercase()
                val filtered = allStudents.filter {
                    it.name.lowercase().contains(q) ||
                            it.studentId.lowercase().contains(q)
                }.toMutableList()
                adapter.updateList(filtered)
            }
        })

        // ── Big buttons ──
        btnAttend.setOnClickListener {
            startActivity(Intent(this, AttendanceActivity::class.java))
        }
        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }

        // ── Bottom nav ──
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_roster  // highlight Roster tab

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_roster -> true  // already here
                R.id.nav_attend -> {
                    startActivity(Intent(this, AttendanceActivity::class.java))
                    true
                }
                R.id.nav_add -> {
                    startActivity(Intent(this, AddStudentActivity::class.java))
                    true
                }
                R.id.nav_report -> {
                    startActivity(Intent(this, ReportActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
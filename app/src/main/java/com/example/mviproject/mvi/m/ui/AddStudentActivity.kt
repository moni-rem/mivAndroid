package com.example.mviproject.mvi.m.ui

import android.os.Bundle
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mviproject.R

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val etName  = findViewById<EditText>(R.id.etStudentName)
        val etId    = findViewById<EditText>(R.id.etStudentId)
        val btnSave = findViewById<Button>(R.id.btnSaveStudent)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val id   = etId.text.toString().trim()

            if (name.isEmpty() || id.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Student $name added!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
        // ── Bottom nav ──
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_add
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_roster -> {
                    startActivity(Intent(this, SummaryActivity::class.java))
                    true
                }
                R.id.nav_attend -> {
                    startActivity(Intent(this, AttendanceActivity::class.java))
                    true
                }
                R.id.nav_add -> true
                R.id.nav_report -> {
                    startActivity(Intent(this, ReportActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
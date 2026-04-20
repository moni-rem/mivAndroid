package com.example.mviproject.mvi.m.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mviproject.R
import com.example.mviproject.mvi.m.Student
import com.example.mviproject.mvi.m.StudentRepository

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
                Toast.makeText(this, "Please fill in all fields!",
                    Toast.LENGTH_SHORT).show()
            } else {
                // Create a new student and add it to the repository
                val newStudent = Student(name, id, true)
                StudentRepository.addStudent(newStudent)
                
                Toast.makeText(this, "Student $name added!",
                    Toast.LENGTH_SHORT).show()
                finish() // Go back to the previous screen (SummaryActivity)
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
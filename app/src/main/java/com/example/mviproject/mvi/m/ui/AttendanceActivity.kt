package com.example.mviproject.mvi.m.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mviproject.R
import com.example.mviproject.mvi.m.Student
import com.example.mviproject.mvi.m.adapter.AttendanceAdapter
import com.example.mviproject.mvi.m.intent.AttendanceIntent
import com.example.mviproject.mvi.m.state.AttendanceState
import com.example.mviproject.mvi.m.viewmodel.AttendanceViewModel
import kotlinx.coroutines.launch

class AttendanceActivity : AppCompatActivity() {

    private val viewModel: AttendanceViewModel by viewModels()
    private lateinit var adapter: AttendanceAdapter
    private var currentStudents: List<Student> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvStudents)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar) // Ensure this exists in activity_main.xml or handle null
        
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        // Observe State
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is AttendanceState.Idle -> {
                        viewModel.handleIntent(AttendanceIntent.LoadStudents)
                    }
                    is AttendanceState.Loading -> {
                        progressBar?.visibility = View.VISIBLE
                    }
                    is AttendanceState.Success -> {
                        progressBar?.visibility = View.GONE
                        currentStudents = state.students
                        adapter = AttendanceAdapter(state.students.toMutableList())
                        recyclerView.adapter = adapter
                    }
                    is AttendanceState.Error -> {
                        progressBar?.visibility = View.GONE
                        Toast.makeText(this@AttendanceActivity, state.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        findViewById<android.widget.Button>(R.id.btnSaveAttendance).setOnClickListener {
            goToReport()
        }
        
        findViewById<android.widget.Button>(R.id.btnGoBack).setOnClickListener {
            finish()
        }
    }

    private fun goToReport() {
        val intent = Intent(this, ReportActivity::class.java)
        intent.putStringArrayListExtra("names", ArrayList(currentStudents.map { it.name }))
        intent.putStringArrayListExtra("ids", ArrayList(currentStudents.map { it.studentId }))
        intent.putExtra("statuses", currentStudents.map { it.isPresent }.toBooleanArray())
        intent.putIntegerArrayListExtra("avatars", ArrayList(currentStudents.map { it.avatar }))
        startActivity(intent)
    }
}
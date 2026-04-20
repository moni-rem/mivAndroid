package com.example.mviproject.mvi.m.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviproject.mvi.m.StudentRepository
import com.example.mviproject.mvi.m.intent.AttendanceIntent
import com.example.mviproject.mvi.m.state.AttendanceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AttendanceViewModel : ViewModel() {

    private val _state = MutableStateFlow<AttendanceState>(AttendanceState.Idle)
    val state: StateFlow<AttendanceState> get() = _state

    fun handleIntent(intent: AttendanceIntent) {
        when (intent) {
            is AttendanceIntent.LoadStudents -> loadStudents()
            is AttendanceIntent.MarkAttendance -> markAttendance(intent.studentId, intent.isPresent)
        }
    }

    private fun loadStudents() {
        viewModelScope.launch {
            _state.value = AttendanceState.Loading
            try {
                val students = StudentRepository.getStudents()
                _state.value = AttendanceState.Success(students)
            } catch (e: Exception) {
                _state.value = AttendanceState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun markAttendance(studentId: String, isPresent: Boolean) {
        // Logic to update student status in repository would go here
        // For now, we just refresh the state
        loadStudents()
    }
}
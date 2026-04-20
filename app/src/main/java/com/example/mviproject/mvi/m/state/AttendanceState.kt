package com.example.mviproject.mvi.m.state
import com.example.mviproject.mvi.m.Student
sealed class AttendanceState {
    object Idle : AttendanceState()
    object Loading : AttendanceState()
    data class Success(val students: List<Student>) : AttendanceState()
    data class Error(val message: String) : AttendanceState()
}
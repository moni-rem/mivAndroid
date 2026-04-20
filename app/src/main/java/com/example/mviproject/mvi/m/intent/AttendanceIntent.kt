package com.example.mviproject.mvi.m.intent

sealed class AttendanceIntent {
    object LoadStudents : AttendanceIntent()
    data class MarkAttendance(val studentId: String, val isPresent: Boolean) : AttendanceIntent()
}
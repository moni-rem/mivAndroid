package com.example.mviproject.mvi.m

import com.example.mviproject.R

data class Student(
    val name: String,
    val studentId: String,
    val isActive: Boolean,
    var isPresent: Boolean = false,
    var hasTakenAttendance: Boolean = false,
    val avatar: Int = R.drawable.img
)
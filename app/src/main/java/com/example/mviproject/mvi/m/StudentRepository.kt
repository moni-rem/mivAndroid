package com.example.mviproject.mvi.m

object StudentRepository {
    private val allStudents = mutableListOf(
        Student("John Doe", "STU001", true),
        Student("Jane Smith", "STU002", true),
        Student("Mike Lee", "STU003", false),
        Student("Sarah Park", "STU004", true)
    )

    fun getStudents(): MutableList<Student> = allStudents

    fun addStudent(student: Student) {
        allStudents.add(student)
    }
}
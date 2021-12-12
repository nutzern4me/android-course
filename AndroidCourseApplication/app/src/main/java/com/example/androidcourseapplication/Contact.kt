package com.example.androidcourseapplication

data class Contact(
    val id: Int,
    val photo: Int,
    val name: String,
    val phoneNums: List<String>,
    val emails: List<String>,
    val description: String
) {
}
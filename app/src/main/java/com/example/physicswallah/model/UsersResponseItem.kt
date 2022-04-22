package com.example.physicswallah.model

data class UsersResponseItem(
    val id: Int,
    val name: String,
    val profileImage: String,
    val qualification: List<String>,
    val subjects: List<String>
)
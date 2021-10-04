package com.example.hbapplicationgroupb.model.updateUserData

data class PostUpdateUserData(
    val firstName: String,
    val lastName: String,
    val emailAddress: String,
    val userName: String,
    val password: String,
    val phoneNumber: String,
    val gender: String,
    val age: Int
)

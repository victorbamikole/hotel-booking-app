package com.example.hbapplicationgroupb.model.userRegistration

data class PostUserRegistrationData(
    val firstName: String,
    val lastName: String,
    val emailAddress: String,
    val userName: String,
    val password: String,
    val phoneNumber: String,
    val gender: String,
    val age: Int
)

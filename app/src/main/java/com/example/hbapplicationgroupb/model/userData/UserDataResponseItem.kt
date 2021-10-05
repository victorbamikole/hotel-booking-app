package com.example.hbapplicationgroupb.model.userData

data class UserDataResponseItem(
    val firstName: String,
    val lastName: String,
    val emailAddress: String,
    val userName: String,
    val password: String,
    val phoneNumber: String,
    val gender: String,
    val age: Int
)

package com.example.hbapplicationgroupb.model.updateUserData

data class PostUpdateUserData (
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val age: Int?= null,
    val creditCard: String,
    val address: String,
    val state: String,
    val updatedAt: String,
)

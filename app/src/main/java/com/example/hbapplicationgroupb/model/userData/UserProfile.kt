package com.example.hbapplicationgroupb.model.userData

data class UserProfile(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)
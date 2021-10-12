package com.example.hbapplicationgroupb.model.resetPassword

data class PostResetPasswordData(
    val token: String,
    val email: String,
    val newPassword: String,
    val confirmPassword: String,
)

package com.example.hbapplicationgroupb.model.updateUserPassword

data class UpdateUserPasswordResponse(
    val statusCode: String,
    val success: Boolean,
    val message: String,
    val errors: String? = null
)

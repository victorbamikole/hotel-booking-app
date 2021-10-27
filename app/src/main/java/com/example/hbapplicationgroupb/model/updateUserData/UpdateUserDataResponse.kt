package com.example.hbapplicationgroupb.model.updateUserData

data class UpdateUserDataResponse(
    val statusCode: String,
    val success: Boolean,
    val data: PostUpdateUserData,
    val message: String,
    val errors: String? = null
)

package com.example.hbapplicationgroupb.model.userData

data class UserDataResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<UserDataResponseItem>,
    val message: String,
    val errors: String? = null
)

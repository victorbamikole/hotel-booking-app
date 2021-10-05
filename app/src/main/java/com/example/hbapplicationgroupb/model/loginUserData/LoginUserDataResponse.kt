package com.example.hbapplicationgroupb.model.loginUserData

data class LoginUserDataResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<LoginUserDataResponseItem>,
    val message: String,
    val errors: String? = null
)

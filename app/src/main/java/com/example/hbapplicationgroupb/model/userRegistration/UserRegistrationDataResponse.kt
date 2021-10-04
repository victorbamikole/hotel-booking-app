package com.example.hbapplicationgroupb.model.userRegistration

data class UserRegistrationDataResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<UserRegistrationDataResponseId>,
    val message: String,
    val errors: String? = null
)

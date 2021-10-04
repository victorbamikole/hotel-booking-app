package com.example.hbapplicationgroupb.model.forgotPasswordData

data class ForgotPasswordDataResponse(
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<ForgotPasswordDataResponseId>,
    val message: String,
    val errors: String? = null
)

package com.example.hbapplicationgroupb.model.forgotPasswordData

data class ForgotPasswordDataResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<ForgotPasswordDataResponseId>,
    val message: String,
)

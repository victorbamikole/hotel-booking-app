package com.example.hbapplicationgroupb.model.resetPassword

data class ResetPasswordDataResponse(
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<ResetPasswordDataResponseId>,
    val message: String,
    val errors: String? = null
)

package com.example.hbapplicationgroupb.model.forgotPasswordData

data class ForgotPasswordDataResponse(

    val statusCode: Int,
    val succeeded: Boolean,
    val data: String,
    val message: String,
)

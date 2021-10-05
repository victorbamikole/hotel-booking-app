package com.example.hbapplicationgroupb.model.updateUserData

data class UpdateUserDataResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<UpdateUserDataResponseId>,
    val message: String,
    val errors: String? = null
)

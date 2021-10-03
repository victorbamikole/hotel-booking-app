package com.example.hbapplicationgroupb.model.userData

data class UserData(
    val statusCode: String,
    val success: Boolean,
    val data: List<UserDataItems>,
    val message: String,
    val errors: String? = null
)

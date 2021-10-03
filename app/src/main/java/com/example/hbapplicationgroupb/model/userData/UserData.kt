package com.example.hbapplicationgroupb.model.userData

data class UserData(
    val statusCode: String,
    val success: Boolean,
    val Data: List<UserDataItems>,
    val Message: String,
    val errors: String? = null
)

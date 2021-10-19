package com.example.hbapplicationgroupb.model.userData

data class UserDataResponse(
    val statusCode: Int,
    val succeeded: Boolean,
    val data: BasicUserData,
    val message: String,
)
data class BasicUserData(
    val id:String,
    val token:String,
)

package com.example.hbapplicationgroupb.model.userData

data class UserDataResponse(
    val data: BasicUserData,
    val succeeded: Boolean,
    val message: String,
    val statusCode: Int,
)



data class BasicUserData(
    val id:String,
    val token:String,
)

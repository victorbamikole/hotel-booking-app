package com.example.hbapplicationgroupb.model.loginUserData

data class LoginUserDataResponse(
    val data : LoginUserDataResponseItem,
    val succeeded : Boolean,
    val message : String,
    val statusCode : Int
)

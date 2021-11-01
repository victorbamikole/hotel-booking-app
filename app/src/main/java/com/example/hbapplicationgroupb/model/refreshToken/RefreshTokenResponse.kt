package com.example.hbapplicationgroupb.model.refreshToken

data class RefreshTokenResponse(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)
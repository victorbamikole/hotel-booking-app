package com.example.hbapplicationgroupb.model.userHotelsData


data class UserHotelDataResponse (
    val statusCode: String,
    val success: Boolean,
    val data: List<UserHotelDataItem>,
    val message: String,
    val errors: String? = null
)

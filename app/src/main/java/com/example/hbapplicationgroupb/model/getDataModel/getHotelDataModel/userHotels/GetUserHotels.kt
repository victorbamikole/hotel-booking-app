package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.userHotels

data class GetUserHotels(
    val statusCode: String,
    val success: String,
    val Data: List<GetUserHotelsItem>,
    val Message: String,
    val errors: String? = null
)

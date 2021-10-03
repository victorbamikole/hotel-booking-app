package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.userHotels

data class GetUserHotels(
    val statusCode: String,
    val success: String,
    val data: List<GetUserHotelsItem>,
    val message: String,
    val errors: String? = null
)

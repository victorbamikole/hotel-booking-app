package com.example.hbapplicationgroupb.model.hotelDescriptionData

data class HotelDescriptionResponse(
    val data: HotelDescriptionData,
    val message: Any,
    val statusCode: Int,
    val succeeded: Boolean
)
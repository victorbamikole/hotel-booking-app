package com.example.hbapplicationgroupb.model.allHotels

data class GetAllHotel(
    val data: List<HotelData>,
    val message: Any,
    val statusCode: Int,
    val succeeded: Boolean
)
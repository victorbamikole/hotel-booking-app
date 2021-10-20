package com.example.hbapplicationgroupb.model.tophotelresponse

data class AllTopHotels(
    val data: List<TopHotelData>,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)
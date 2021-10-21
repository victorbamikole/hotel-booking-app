package com.example.hbapplicationgroupb.model.hotelSearchResponse

data class HotelSearchResponse(
    val data: List<Data>,
    val message: Any?,
    val statusCode: Int,
    val succeeded: Boolean
)
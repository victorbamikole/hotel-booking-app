package com.example.hbapplicationgroupb.model.hotelBooking

data class RoomsAvailable(
    val `data`: List<Data>,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)
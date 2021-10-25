package com.example.hbapplicationgroupb.model.addCustomerRating.hotelRating

data class RatingResponse(
    val data: ArrayList<HotelRating>,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)
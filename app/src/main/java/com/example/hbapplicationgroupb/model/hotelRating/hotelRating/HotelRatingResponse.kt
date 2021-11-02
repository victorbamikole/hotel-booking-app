package com.example.hbapplicationgroupb.model.hotelRating.hotelRating

data class HotelRatingResponse(
    val `data`: List<HotelRating>,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)
package com.example.hbapplicationgroupb.model.hotelRating.hotelRating

data class HotelReview(
    val data: RatingData,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean

)
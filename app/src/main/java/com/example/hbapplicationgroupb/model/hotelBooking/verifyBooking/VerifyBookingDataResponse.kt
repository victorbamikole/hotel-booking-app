package com.example.hbapplicationgroupb.model.hotelBooking.verifyBooking

data class VerifyBookingDataResponse(
    val `data`: String,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)
package com.example.hbapplicationgroupb.model.hotelBooking

data class HotelBookingResponse(
    val data:BookingData,
    val succeeded:Boolean,
    val message:String,
    val statusCode:Int

)

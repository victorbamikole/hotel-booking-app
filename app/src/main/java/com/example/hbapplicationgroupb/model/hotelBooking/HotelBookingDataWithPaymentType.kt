package com.example.hbapplicationgroupb.model.hotelBooking


data class HotelBookingDataWithPaymentType(
    val roomId: String,
    val checkIn:String,
    val checkOut:String,
    val noOfPeople:Int,
    val paymentService:String
)

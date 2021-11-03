package com.example.hbapplicationgroupb.model.hotelBooking

data class BookingData(
    val bookingReference:String,
    val roomNo:Int,
    val checkIn:String,
    val checkOut:String,
    val noOfPeople:Int,
    val hotel:String,
    val roomType:String,
    val paymentStatus:Boolean,
    val price:Double,
    val paymentReference:String,
    val paymentUrl:String
)
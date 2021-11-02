package com.example.hbapplicationgroupb.model.hotelBooking

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HotelBookingData (
    val roomId: String,
    val checkIn:String,
    val checkOut:String,
    val noOfPeople:Int,
    val price:String
        ): Parcelable
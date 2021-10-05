package com.example.hbapplicationgroupb.model.customerBookingData

import java.util.*

data class CustomerBookingDataItem(
    val bookingReference: String,
    val checkIn: Date,
    val checkOut: Date,
    val numberOfPeople: Int,
    val serviceName: String
)

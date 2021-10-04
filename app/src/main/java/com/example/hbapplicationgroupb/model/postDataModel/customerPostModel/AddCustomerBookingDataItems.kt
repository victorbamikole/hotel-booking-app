package com.example.hbapplicationgroupb.model.postDataModel.customerPostModel

import java.util.*

data class AddCustomerBookingDataItems(
    val bookingReference: String,
    val checkIn: Date,
    val checkOut: Date,
    val numberOfPeople: Int,
    val serviceName: String
)

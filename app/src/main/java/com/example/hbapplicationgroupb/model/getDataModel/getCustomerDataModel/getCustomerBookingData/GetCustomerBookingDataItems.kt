package com.example.hbapplicationgroupb.model.getDataModel.getCustomerDataModel.getCustomerBookingData

import java.util.*


data class GetCustomerBookingDataItems(
    val bookingReference: String,
    val checkIn: Date,
    val checkOut: Date,
    val numberOfPeople: Int,
    val serviceName: String
)

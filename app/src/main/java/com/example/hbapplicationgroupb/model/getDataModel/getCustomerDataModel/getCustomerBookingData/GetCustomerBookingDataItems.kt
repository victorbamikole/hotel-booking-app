package com.example.hbapplicationgroupb.model.getDataModel.getCustomerDataModel.getCustomerBookingData

import java.util.*

data class GetCustomerBookingDataItems(
    val BookingReference: String,
    val CheckIn: Date,
    val CheckOut: Date,
    val NumberOfPeople: Int,
    val ServiceName: String
)

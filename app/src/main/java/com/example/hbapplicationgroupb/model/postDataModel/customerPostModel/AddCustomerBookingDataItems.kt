package com.example.hbapplicationgroupb.model.postDataModel.customerPostModel

import java.util.*

data class AddCustomerBookingDataItems(
    val BookingReference: String,
    val CheckIn: Date,
    val CheckOut: Date,
    val NumberOfPeople: Int,
    val ServiceName: String
)

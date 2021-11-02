package com.example.hbapplicationgroupb.model.customerBookingData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Customer_Booking_History_Table")
data class CustomerBookingDataItem(
    @PrimaryKey
    val roomId: Int,
//    val bookingReference: String,
    val checkIn: String,
    val checkOut: String,
    val noOfPeople: Int,
    val paymentService: String
)

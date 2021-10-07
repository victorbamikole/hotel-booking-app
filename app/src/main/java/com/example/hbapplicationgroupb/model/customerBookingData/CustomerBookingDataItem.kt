package com.example.hbapplicationgroupb.model.customerBookingData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Customer_Booking_History_Table")
data class CustomerBookingDataItem(
    @PrimaryKey
    val id: Int,
    val bookingReference: String,
    val checkIn: String,
    val checkOut: String,
    val numberOfPeople: Int,
    val serviceName: String
)

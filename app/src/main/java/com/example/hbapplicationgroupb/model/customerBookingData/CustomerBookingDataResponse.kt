package com.example.hbapplicationgroupb.model.customerBookingData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer Booking History")
data class CustomerBookingDataResponse(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<CustomerBookingDataItem>,
    val message: String,
    val errors: String? = null
)

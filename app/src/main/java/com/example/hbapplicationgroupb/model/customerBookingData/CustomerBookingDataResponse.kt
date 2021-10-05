package com.example.hbapplicationgroupb.model.customerBookingData

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CustomerBookingDataResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<CustomerBookingDataItem>,
    val message: String,
    val errors: String? = null
)

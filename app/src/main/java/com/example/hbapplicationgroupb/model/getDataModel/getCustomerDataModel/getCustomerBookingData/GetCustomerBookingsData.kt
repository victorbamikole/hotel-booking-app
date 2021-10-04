package com.example.hbapplicationgroupb.model.getDataModel.getCustomerDataModel.getCustomerBookingData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer Booking History")
data class GetCustomerBookingsData(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<GetCustomerBookingDataItems>,
    val message: String,
    val errors: String? = null
)

package com.example.hbapplicationgroupb.model.customerBookingData

data class CustomerBookingDataResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<CustomerBookingDataItem>,
    val message: String,
    val errors: String? = null
)

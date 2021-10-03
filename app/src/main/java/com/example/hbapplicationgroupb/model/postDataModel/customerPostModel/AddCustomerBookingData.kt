package com.example.hbapplicationgroupb.model.postDataModel.customerPostModel

data class AddCustomerBookingData(
    val statusCode: String,
    val success: Boolean,
    val data: List<AddCustomerBookingDataItems>,
    val message: String,
    val errors: String? = null
)

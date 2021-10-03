package com.example.hbapplicationgroupb.model.postDataModel.customerPostModel

data class AddCustomerBookingData(
    val statusCode: String,
    val success: Boolean,
    val Data: List<AddCustomerBookingDataItems>,
    val message: String,
    val error: String? = null
)

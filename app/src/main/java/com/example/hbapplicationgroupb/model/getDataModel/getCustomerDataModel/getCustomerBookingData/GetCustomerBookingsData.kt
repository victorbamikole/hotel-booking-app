package com.example.hbapplicationgroupb.model.getDataModel.getCustomerDataModel.getCustomerBookingData

import com.example.hbapplicationgroupb.model.getDataModel.getCustomerDataModel.getCustomerBookingData.GetCustomerBookingDataItems

data class GetCustomerBookingsData(
    val statusCode: String,
    val success: Boolean,
    val data: List<GetCustomerBookingDataItems>,
    val message: String,
    val errors: String? = null
)

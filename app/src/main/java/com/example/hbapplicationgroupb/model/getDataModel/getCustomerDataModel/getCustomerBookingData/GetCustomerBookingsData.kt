package com.example.hbapplicationgroupb.model.getDataModel.getCustomerDataModel.getCustomerBookingData

import com.example.hbapplicationgroupb.model.getDataModel.getCustomerDataModel.getCustomerBookingData.GetCustomerBookingDataItems

data class GetCustomerBookingsData(
    val statusCode: String,
    val success: Boolean,
    val Data: List<GetCustomerBookingDataItems>,
    val message: String,
    val error: String? = null
)

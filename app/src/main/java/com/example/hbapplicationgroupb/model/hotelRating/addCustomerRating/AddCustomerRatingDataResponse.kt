package com.example.hbapplicationgroupb.model.hotelRating.addCustomerRating

import com.example.hbapplicationgroupb.model.hotelRating.addCustomerRating.AddCustomerRatingDataItem

data class AddCustomerRatingDataResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<AddCustomerRatingDataItem>,
    val message: String,
    val errors: String? = null
)

package com.example.hbapplicationgroupb.model.addCustomerRating

data class AddCustomerRatingDataResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<AddCustomerRatingDataItem>,
    val message: String,
    val errors: String? = null
)

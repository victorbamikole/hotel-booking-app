package com.example.hbapplicationgroupb.model.postDataModel.customerPostModel

data class AddCustomerReview(
    val statusCode: Int,
    val success: Boolean,
    val Data: List<AddCustomerReviewItem>,
    val message: String
)

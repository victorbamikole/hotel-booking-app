package com.example.hbapplicationgroupb.model.customerWishList

data class CustomerWishListResponse(
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<CustomerWishListItem>,
    val message: String,
    val errors: String? = null
)

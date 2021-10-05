package com.example.hbapplicationgroupb.model.customerWishList

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CustomerWishListResponse(
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<CustomerWishListItem>,
    val message: String,
    val errors: String? = null
)

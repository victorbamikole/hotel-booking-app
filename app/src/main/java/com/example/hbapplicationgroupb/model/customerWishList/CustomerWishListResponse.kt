package com.example.hbapplicationgroupb.model.customerWishList

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer WishList")
data class CustomerWishListResponse(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<CustomerWishListItem>,
    val message: String,
    val errors: String? = null
)

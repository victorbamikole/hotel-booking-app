package com.example.hbapplicationgroupb.model.customerWishList

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Customer_WishList_Table")
data class CustomerWishListItem(
    @PrimaryKey
    val id: Int,
    val hotelId: String,
    val hotelName: String,
    val imageUrl: String
)

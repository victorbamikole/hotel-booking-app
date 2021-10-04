package com.example.hbapplicationgroupb.model.getDataModel.getCustomerDataModel.getCustomerWishListData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer WishList")
data class GetCustomerWishListData(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<GetCustomerWishListDataItem>,
    val message: String,
    val errors: String? = null
)

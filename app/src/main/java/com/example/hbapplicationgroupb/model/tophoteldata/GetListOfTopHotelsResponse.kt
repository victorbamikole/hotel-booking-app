package com.example.hbapplicationgroupb.model.tophoteldata

import androidx.room.Entity
import androidx.room.PrimaryKey


data class GetListOfTopHotelsResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<HotelTopDealItems>,
    val message: String,
    val errors: String? = null
)
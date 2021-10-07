package com.example.hbapplicationgroupb.model.userHotelsData

import androidx.room.Entity
import androidx.room.PrimaryKey


data class UserHotelDataResponse (
    val statusCode: String,
    val success: Boolean,
    val data: List<UserHotelDataItem>,
    val message: String,
    val errors: String? = null
)

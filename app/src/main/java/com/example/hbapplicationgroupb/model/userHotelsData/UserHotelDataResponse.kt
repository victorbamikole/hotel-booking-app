package com.example.hbapplicationgroupb.model.userHotelsData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user Hotels")
data class UserHotelDataResponse(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<UserHotelDataItem>,
    val message: String,
    val errors: String? = null
)

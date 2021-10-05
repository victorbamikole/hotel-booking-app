package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.userHotels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User Hotels")
data class GetUserHotels(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: String,
    val data: List<GetUserHotelsItem>,
    val message: String,
    val errors: String? = null
)

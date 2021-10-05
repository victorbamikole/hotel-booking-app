package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.allHotels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hotel Names")
data class GetAllHotels(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<GetAllHotelsItem>,
    val message: String,
    val errors: String? = null
)

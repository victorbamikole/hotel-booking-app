package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelDescriptionData

import androidx.room.Entity

@Entity(tableName = "Hotel Description")
data class HotelDescriptionData(
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<HotelDescriptionDataItems>,
    val message: String,
    val errors: String? = null
)

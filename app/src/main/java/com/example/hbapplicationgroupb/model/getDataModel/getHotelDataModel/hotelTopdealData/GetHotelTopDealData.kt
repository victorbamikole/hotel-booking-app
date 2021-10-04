package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelTopdealData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hotel top Deals")
data class GetHotelTopDealData(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<GetHotelTopDealDataItem>,
    val message: String,
    val errors: String? = null
)

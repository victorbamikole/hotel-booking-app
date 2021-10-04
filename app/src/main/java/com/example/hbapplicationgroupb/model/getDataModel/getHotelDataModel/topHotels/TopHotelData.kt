package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.topHotels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hbapplicationgroupb.model.hotelTopDeals.TopHotelItems

@Entity(tableName = "Top Hotels")
data class TopHotelData(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<TopHotelItems>,
    val message: String,
    val errors: String? = null
)

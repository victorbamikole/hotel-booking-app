package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelAmenities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hotel Amenities")
data class GetHotelAmenities(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: String,
    val data: List<GetHotelAmenitiesItem>,
    val message: String,
    val errors: String? = null
)

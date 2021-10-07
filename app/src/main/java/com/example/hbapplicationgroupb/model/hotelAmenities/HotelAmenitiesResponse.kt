package com.example.hbapplicationgroupb.model.hotelAmenities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class HotelAmenitiesResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<GetHotelAmenitiesItem>,
    val message: String,
    val errors: String? = null
)

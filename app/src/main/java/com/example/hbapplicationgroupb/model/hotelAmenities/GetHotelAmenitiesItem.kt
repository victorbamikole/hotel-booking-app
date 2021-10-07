package com.example.hbapplicationgroupb.model.hotelAmenities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Hotel_Amenities_Table")
data class GetHotelAmenitiesItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Double,
    val discount: String
)

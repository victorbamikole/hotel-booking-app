package com.example.hbapplicationgroupb.model.allHotels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "hotel_detail")
data class HotelData(
    val address: String,
    val city: String,
    val description: String,
    val email: String,
    val featuredImage: String,
    val gallery: List<String>,
    @PrimaryKey
    val id: String,
    val name: String,
    val phone: String,
    val rating: Double,
    @Embedded
    val reviews: Any,
    val roomTypes: List<RoomType>,
    val state: String
)


package com.example.hbapplicationgroupb.model.tophotelresponse

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hbapplicationgroupb.util.constants.TOP_HOTEL_TABLE


@Entity(tableName = TOP_HOTEL_TABLE)
data class TopHotelData(
    val description: String,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val percentageRating: Int,
    val price: Double,
    val thumbnail: String
)
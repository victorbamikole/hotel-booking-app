package com.example.hbapplicationgroupb.model.topDealAndHotel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Top_Deals_Table")
data class TopDealAndHotelData(
    val description: String,
    val percentageRating: Double,
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Double,
    val thumbnail: String
)
package com.example.hbapplicationgroupb.model.allhotel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotel_detail")
data class PageItem(
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
    val roomTypes: List<RoomType>,
    val state: String,
    val saved:Boolean = false
)
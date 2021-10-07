package com.example.hbapplicationgroupb.model.hotelDescriptionData

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Hotel_Description_Table")
data class HotelDescriptionDataItems(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val email: String,
    val address: String,
    val city: String,
    val rating: Double,
    val gallery: String,
    @Embedded
    val reviews: ReviewsItem,
    @Embedded
    val roomTypes: RoomTypesItem
)

package com.example.hbapplicationgroupb.model.userHotelsData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_Hotels_Table")
data class UserHotelDataItem(
    @PrimaryKey
    val id: Int,
    val hotelId: String,
    val hotelName: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val city: String,
    val state: String
)

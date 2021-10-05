package com.example.hbapplicationgroupb.model.allHotels

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "All_Hotels_Item_Table")
data class GetAllHotelsItem(
    @PrimaryKey
    val id: Int,
    val isBooked: Boolean,
    val roomType: String
)

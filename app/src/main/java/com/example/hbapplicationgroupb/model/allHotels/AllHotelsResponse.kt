package com.example.hbapplicationgroupb.model.allHotels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hotel Names")
data class AllHotelsResponse(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<GetAllHotelsItem>,
    val message: String,
    val errors: String? = null
)

package com.example.hbapplicationgroupb.model.allHotels

import androidx.room.Entity
import androidx.room.PrimaryKey

data class AllHotelsResponse(
    val data: List<GetAllHotelsItem>,
    val success: Boolean,
    val message: String? = null,
    val statusCode: Int,
)

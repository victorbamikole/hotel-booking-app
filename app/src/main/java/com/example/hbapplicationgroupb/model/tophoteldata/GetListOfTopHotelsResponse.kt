package com.example.hbapplicationgroupb.model.tophoteldata

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Top Hotels")
data class GetListOfTopHotelsResponse(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<TopHotelItems>,
    val message: String,
    val errors: String? = null
)
package com.example.hbapplicationgroupb.model.hotelDescriptionData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hotel Description")
data class GetListOfHotelDescriptionResponse(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<HotelDescriptionDataItems>,
    val message: String,
    val errors: String? = null
)

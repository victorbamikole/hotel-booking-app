package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelRatingsData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hotel Ratings")
data class GetHotelsRatingData(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success:Boolean,
    val data: List<GetHotelsRatingDataItem>,
    val message: String,
    val errors: String? = null
)

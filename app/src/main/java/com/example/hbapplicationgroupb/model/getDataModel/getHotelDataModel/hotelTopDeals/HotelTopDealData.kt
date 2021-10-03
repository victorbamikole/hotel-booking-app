package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelTopDeals

import com.example.hbapplicationgroupb.model.hotelTopDeals.HotelTopDealItems

data class HotelTopDealData(
    val statusCode: String,
    val success: Boolean,
    val Data: List<HotelTopDealItems>,
    val Message: String,
    val errors: String? = null
)

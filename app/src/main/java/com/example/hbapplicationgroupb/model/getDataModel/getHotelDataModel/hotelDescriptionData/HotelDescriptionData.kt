package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelDescriptionData

data class HotelDescriptionData(
    val statusCode: String,
    val success: Boolean,
    val data: List<HotelDescriptionDataItems>,
    val message: String,
    val errors: String? = null
)

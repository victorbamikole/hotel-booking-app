package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelDescriptionData

data class HotelDescriptionData(
    val statusCode: String,
    val success: Boolean,
    val Data: List<HotelDescriptionDataItems>,
    val Message: String,
    val errors: String? = null
)

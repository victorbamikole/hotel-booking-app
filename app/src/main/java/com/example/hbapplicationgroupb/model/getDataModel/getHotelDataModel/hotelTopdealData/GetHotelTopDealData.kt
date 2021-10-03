package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelTopdealData

data class GetHotelTopDealData(
    val statusCode: String,
    val success: Boolean,
    val Data: List<GetHotelTopDealDataItem>,
    val Message: String,
    val errors: String? = null
)

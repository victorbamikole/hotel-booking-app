package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelTopdealData

data class GetHotelTopDealData(
    val statusCode: String,
    val success: Boolean,
    val data: List<GetHotelTopDealDataItem>,
    val message: String,
    val errors: String? = null
)

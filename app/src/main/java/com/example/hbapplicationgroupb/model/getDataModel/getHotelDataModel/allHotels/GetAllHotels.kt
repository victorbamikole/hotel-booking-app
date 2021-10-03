package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.allHotels

data class GetAllHotels(
    val statusCode: String,
    val success: Boolean,
    val Data: List<GetAllHotelsItem>,
    val Message: String,
    val error: String? = null
)

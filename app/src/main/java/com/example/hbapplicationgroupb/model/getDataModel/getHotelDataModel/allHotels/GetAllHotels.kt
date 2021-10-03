package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.allHotels

data class GetAllHotels(
    val statusCode: String,
    val success: Boolean,
    val data: List<GetAllHotelsItem>,
    val message: String,
    val errors: String? = null
)

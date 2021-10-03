package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelAmenities

data class GetHotelAmenities(
    val statusCode: String,
    val success: String,
    val Data: List<GetHotelAmenitiesItem>,
    val Message: String,
    val errors: String? = null
)

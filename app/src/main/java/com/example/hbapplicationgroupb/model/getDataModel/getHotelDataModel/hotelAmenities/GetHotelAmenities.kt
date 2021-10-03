package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelAmenities

data class GetHotelAmenities(
    val statusCode: String,
    val success: String,
    val data: List<GetHotelAmenitiesItem>,
    val message: String,
    val errors: String? = null
)

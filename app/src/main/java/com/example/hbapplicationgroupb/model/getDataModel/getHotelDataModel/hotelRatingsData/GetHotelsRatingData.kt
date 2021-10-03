package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelRatingsData

data class GetHotelsRatingData(
    val statusCode: String,
    val success:Boolean,
    val data: List<GetHotelsRatingDataItem>,
    val message: String,
    val errors: String? = null
)

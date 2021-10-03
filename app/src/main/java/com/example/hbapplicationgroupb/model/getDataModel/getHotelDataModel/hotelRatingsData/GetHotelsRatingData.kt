package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelRatingsData

data class GetHotelsRatingData(
    val statusCode: String,
    val success:Boolean,
    val Data: List<GetHotelsRatingDataItem>,
    val Message: String,
    val error: String? = null
)

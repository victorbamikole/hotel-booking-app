package com.example.hbapplicationgroupb.model.topDealAndHotel

data class TopDealsAndHotel(
    val data: List<TopDealAndHotelData>,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)
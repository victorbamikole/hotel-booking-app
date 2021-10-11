package com.example.hbapplicationgroupb.model.allHotels

data class GetAllHotel(
    val data: List<Data>,
//    val `data`: List<Data>,
    val message: Any,
    val statusCode: Int,
    val succeeded: Boolean
)
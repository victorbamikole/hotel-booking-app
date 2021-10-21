package com.example.hbapplicationgroupb.model.hotelSearchResponse

data class PageItem(
    val description: String,
    val id: String,
    val name: String,
    val percentageRating: Int,
    val price: Double,
    val thumbnail: String
)
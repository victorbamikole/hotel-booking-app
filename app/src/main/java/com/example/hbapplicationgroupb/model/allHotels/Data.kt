package com.example.hbapplicationgroupb.model.allHotels

data class Data(
    val address: String,
    val city: String,
    val description: String,
    val email: String,
    val featuredImage: String,
    val gallery: List<String>,
    val id: String,
    val name: String,
    val phone: String,
    val rating: Double,
    val reviews: Any,
    val roomTypes: List<RoomType>,
    val state: String
)
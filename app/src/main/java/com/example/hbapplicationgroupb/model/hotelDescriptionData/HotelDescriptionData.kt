package com.example.hbapplicationgroupb.model.hotelDescriptionData

data class HotelDescriptionData(
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
    val reviews: List<Review>,
    val roomTypes: List<HotelDescriptionRoomType>,
    val state: String
)
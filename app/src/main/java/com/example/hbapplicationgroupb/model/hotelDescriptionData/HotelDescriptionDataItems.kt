package com.example.hbapplicationgroupb.model.hotelDescriptionData

data class HotelDescriptionDataItems(
    val id: String,
    val name: String,
    val description: String,
    val email: String,
    val address: String,
    val city: String,
    val rating: Double,
    val gallery: List<String>,
    val reviews: List<ReviewsItem>,
    val roomTypes: List<RoomTypesItem>
)

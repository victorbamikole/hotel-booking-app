package com.example.hbapplicationgroupb.model.allhotel

data class PageItem(
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
    val roomTypes: List<RoomType>,
    val state: String
)
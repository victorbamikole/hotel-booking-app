package com.example.hbapplicationgroupb.model.getDataModel.getHotelDataModel.hotelDescriptionData

data class HotelDescriptionDataItems(
    val Id: String,
    val Name: String,
    val Description: String,
    val Email: String,
    val Address: String,
    val City: String,
    val Rating: Double,
//    val Gallery: List<GalleryItems>,
    val Reviews: List<ReviewsItem>,
    val RoomTypes: List<RoomTypesItem>
)

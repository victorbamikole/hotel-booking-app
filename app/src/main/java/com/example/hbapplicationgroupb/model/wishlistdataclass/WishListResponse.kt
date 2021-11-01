package com.example.hbapplicationgroupb.model.wishlistdataclass

data class WishListResponse(
    val statusCode:Int,
    val success:Boolean,
    val Data:List<HotelData>,
    val message:String,
    val error:String?
)
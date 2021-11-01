package com.example.hbapplicationgroupb.model.wishlistdataclass

data class WishListResponse(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)
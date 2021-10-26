package com.example.hbapplicationgroupb.model.wishlistdataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishList")
data class WishListDataClass(
    @PrimaryKey
    val id:String,
    val hotelName: String,
    val hotelPrice: Double,
    val description:String,
    val featureImage:String,
    val percentage:String,
    val token:String,
    val saved:Boolean = false
)
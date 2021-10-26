package com.example.hbapplicationgroupb.model.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishList")
data class WishListDataClass(
    @PrimaryKey
    val id:String,
    val hotelName: String,
    val hotelPrice: Double,
    val description:String,
    val percentage:String
)
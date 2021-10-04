package com.example.hbapplicationgroupb.model.hotelTopDeals

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "hotel_Top_Deals_table")
@Parcelize
data class HotelTopDealItems(
    @PrimaryKey
    val id:String,
    val name: String,
    val description: String,
    val email: String,
    val address: String,
    val city: String,
    val state: String
): Parcelable

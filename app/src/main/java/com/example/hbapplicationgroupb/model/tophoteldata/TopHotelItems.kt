package com.example.hbapplicationgroupb.model.tophoteldata

<<<<<<< HEAD:app/src/main/java/com/example/hbapplicationgroupb/model/getDataModel/getHotelDataModel/hotelTopDeals/HotelTopDealItems.kt
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "hotel_Top_Deals_table")
@Parcelize
data class HotelTopDealItems(
    @PrimaryKey
=======
data class TopHotelItems(
>>>>>>> fdb78096e08b0bcf1765d5e20abf06f364f265b8:app/src/main/java/com/example/hbapplicationgroupb/model/tophoteldata/TopHotelItems.kt
    val id:String,
    val name: String,
    val description: String,
    val email: String,
    val address: String,
    val city: String,
    val state: String
): Parcelable

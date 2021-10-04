package com.example.hbapplicationgroupb.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.hotelTopDeals.HotelTopDealItems


@Dao
interface HotelTopDealsItemsDao {

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListOfHotelTopDeals(listOfHotelTopDeals: ArrayList<HotelTopDealItems>)

    @Query("SELECT * FROM hotel_Top_Deals_table ORDER BY Id ASC")
    fun readAllHotelTopDeals(): LiveData<List<HotelTopDealItems>>
}
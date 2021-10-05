package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.topdealsdata.ListOfTopDealsItem
import com.example.hbapplicationgroupb.model.tophoteldata.HotelTopDealItems

interface HotelTopDealItemsDao {

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListOfHotelTopDealItems(listOfHotelTopDealItems: ArrayList<HotelTopDealItems>)

    @Query("SELECT * FROM Top_Hotels_Table ORDER BY Id ASC")
    fun readAllHotelTopDealItems(): LiveData<List<HotelTopDealItems>>
}
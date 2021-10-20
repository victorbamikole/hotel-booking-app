package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//@Dao
//interface HotelTopDealItemsDao {
//
//    @Insert( onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addListOfHotelTopDealItems(listOfHotelTopDealItems: ArrayList<HotelTopDealItems>)
//
//    @Query("SELECT * FROM Top_Hotels_Table ORDER BY Id ASC")
//    fun readAllHotelTopDealItems(): LiveData<List<HotelTopDealItems>>
//}
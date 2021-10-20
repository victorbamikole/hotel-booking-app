package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData

@Dao
interface TopHotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopHotel(topHotel: List<TopHotelData>)

    @Query("SELECT * FROM TOP_HOTEL_TABLE")
    fun getAllTopHotels() : LiveData<List<TopHotelData>>

}
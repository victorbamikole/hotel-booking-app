package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.allHotels.HotelData

@Dao
interface AllHotelDao {


    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllHotelsToDatabase(hotelList : List<HotelData>)

    //Retrieve hotel with given id from room database
    @Query("SELECT * FROM hotel_detail WHERE id ==:hotelId")
    fun getHotelFromDatabase(hotelId :String) :LiveData<HotelData>

    @Query("SELECT * FROM hotel_detail")
    fun getAllHotelsFromRoom() : LiveData<List<HotelData>>

}
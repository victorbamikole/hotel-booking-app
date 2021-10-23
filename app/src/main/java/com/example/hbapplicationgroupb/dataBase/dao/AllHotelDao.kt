package com.example.hbapplicationgroupb.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.allhotel.AllHotel
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import kotlinx.coroutines.flow.Flow

@Dao
interface AllHotelDao {


    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllHotelsToDb(hotelList: List<PageItem>)


    //Retrieve hotel with given id from room database
//    @Query("SELECT * FROM hotel_detail WHERE id ==:hotelId")
//    fun getHotelFromDatabase(hotelId :String) :LiveData<HotelData>

    @Query("SELECT * FROM hotel_detail")
    fun getAllHotelsFromRoom() : Flow<List<PageItem>>

    @Query("DELETE FROM hotel_detail")
    suspend fun deleteAllFromDb()

}
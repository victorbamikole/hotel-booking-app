package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.hotelAmenities.GetHotelAmenitiesItem
@Dao
interface GetHotelAmenitiesItemDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListOfGetHotelAmenitiesItem(listOfGetHotelAmenitiesItem: ArrayList<GetHotelAmenitiesItem>)

    @Query("SELECT * FROM Hotel_Amenities_Table ORDER BY Id ASC")
    fun readAllGetHotelAmenitiesItem(): LiveData<List<GetHotelAmenitiesItem>>
}
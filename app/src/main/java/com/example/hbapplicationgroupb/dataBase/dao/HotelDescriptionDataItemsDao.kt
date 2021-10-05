package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.hotelAmenities.GetHotelAmenitiesItem
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionDataItems

interface HotelDescriptionDataItemsDao {
    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListOfHotelDescriptionDataItems(listOfHotelDescriptionDataItems: ArrayList<HotelDescriptionDataItems>)

    @Query("SELECT * FROM Hotel_Description_Table ORDER BY Id ASC")
    fun readAllHotelDescriptionDataItems(): LiveData<List<HotelDescriptionDataItems>>
}
package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.userHotelsData.UserHotelDataItem

@Dao
interface UserHotelDataItemDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListTopOfUserHotelDataItem(listOfUserHotelDataItem: ArrayList<UserHotelDataItem>)

    @Query("SELECT * FROM user_Hotels_Table ORDER BY Id ASC")
    fun readAllListOfUserHotelDataItem(): LiveData<List<UserHotelDataItem>>
}
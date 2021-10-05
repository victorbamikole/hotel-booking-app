package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.topdealsdata.ListOfTopDealsItem
import com.example.hbapplicationgroupb.model.userHotelsData.UserHotelDataItem

interface UserHotelDataItemDao {

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListTopOfUserHotelDataItem(listOfUserHotelDataItem: ArrayList<UserHotelDataItem>)

    @Query("SELECT * FROM user_Hotels_Table ORDER BY Id ASC")
    fun readAllListOfUserHotelDataItem(): LiveData<List<UserHotelDataItem>>
}
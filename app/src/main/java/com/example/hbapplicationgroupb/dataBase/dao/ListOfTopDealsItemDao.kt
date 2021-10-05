package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionDataItems
import com.example.hbapplicationgroupb.model.topdealsdata.ListOfTopDealsItem

interface ListOfTopDealsItemDao {

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListTopDealsItem(listOfTopDealsItem: ArrayList<ListOfTopDealsItem>)

    @Query("SELECT * FROM Top_Deals_Table ORDER BY Id ASC")
    fun readAllListOfTopDealsItems(): LiveData<List<ListOfTopDealsItem>>
}
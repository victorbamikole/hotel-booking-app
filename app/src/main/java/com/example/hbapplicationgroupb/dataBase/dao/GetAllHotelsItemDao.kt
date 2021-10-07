package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotelsItem
@Dao
interface GetAllHotelsItemDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListOfGetAllHotelsItem(listOfGetAllHotelsItem: ArrayList<GetAllHotelsItem>)


    @Query("SELECT * FROM All_Hotels_Item_Table ORDER BY Id ASC")
    fun readAllGetAllHotelsItem(): LiveData<List<GetAllHotelsItem>>
}
package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotelsItem
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataItem

interface CustomerBookingDataItemDao {

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListOfCustomerBookingDataItem(listOfCustomerBookingDataItem: ArrayList<CustomerBookingDataItem>)

    @Query("SELECT * FROM Customer_Booking_History_Table ORDER BY Id ASC")
    fun readAllCustomerBookingDataItem(): LiveData<List<CustomerBookingDataItem>>
}
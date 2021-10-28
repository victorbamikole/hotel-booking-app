package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataItem
@Dao
interface CustomerBookingDataItemDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListOfCustomerBookingDataItem(listOfCustomerBookingDataItem: ArrayList<CustomerBookingDataItem>)

    @Query("SELECT * FROM Customer_Booking_History_Table ORDER BY roomId ASC")
    fun readAllCustomerBookingDataItem(): LiveData<List<CustomerBookingDataItem>>
}
package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupb.model.customerWishList.CustomerWishListItem

@Dao
interface CustomerWishListItemDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListOfCustomerWishListItem(listOfCustomerWishListItem: ArrayList<CustomerWishListItem>)

    @Query("SELECT * FROM Customer_WishList_Table ORDER BY Id ASC")
    fun readAllCustomerWishListItem(): LiveData<List<CustomerWishListItem>>
}
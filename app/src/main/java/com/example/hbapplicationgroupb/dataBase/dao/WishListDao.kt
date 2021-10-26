package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListDataClass

@Dao
interface WishListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWishedItemToDb(item:WishListDataClass)


    @Delete
    suspend fun deleteWishList(wishList:WishListDataClass)

    @Query("SELECT * FROM wishList WHERE token LIKE :token")
    fun getAllHotelsFromRoom(token:String) : LiveData<List<WishListDataClass>>
}
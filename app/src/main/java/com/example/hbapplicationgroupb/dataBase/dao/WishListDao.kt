package com.example.hbapplicationgroupb.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.model.dataclass.WishListDataClass
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

@Dao
interface WishListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWishedItemToDb(item:WishListDataClass)


    @Delete
    suspend fun deleteWishList(wishList:WishListDataClass)

    @Query("SELECT * FROM wishList")
    fun getAllHotelsFromRoom() : LiveData<List<WishListDataClass>>
}
package com.example.hbapplicationgroupb.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hbapplicationgroupb.model.Hotels
import com.example.hbapplicationgroupb.model.hotelTopDeals.HotelTopDealItems


@Database(entities = [HotelTopDealItems::class], version = 1, exportSchema = false)
abstract class HBDataBase : RoomDatabase() {

//    abstract fun postDao(): PostDao
//    abstract fun commentDao(): CommentDao

    companion object{
        @Volatile
        private var INSTANCE : HBDataBase? = null
        fun getDataBase (context: Context): HBDataBase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    HBDataBase::class.java,
                "app_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
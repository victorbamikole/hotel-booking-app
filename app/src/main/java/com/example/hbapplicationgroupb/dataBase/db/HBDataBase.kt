package com.example.hbapplicationgroupb.dataBase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hbapplicationgroupb.dataBase.dao.*
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotelsItem
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataItem
import com.example.hbapplicationgroupb.model.customerWishList.CustomerWishListItem
import com.example.hbapplicationgroupb.model.hotelAmenities.GetHotelAmenitiesItem
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionDataItems
import com.example.hbapplicationgroupb.model.topdealsdata.ListOfTopDealsItem
import com.example.hbapplicationgroupb.model.tophoteldata.HotelTopDealItems
import com.example.hbapplicationgroupb.model.userHotelsData.UserHotelDataItem


@Database(entities = [GetAllHotelsItem::class,
    CustomerBookingDataItem::class,
    CustomerWishListItem::class,
    GetHotelAmenitiesItem::class,
    HotelDescriptionDataItems::class,
    ListOfTopDealsItem::class,
    HotelTopDealItems::class,
    UserHotelDataItem::class], version = 1, exportSchema = false)
abstract class HBDataBase : RoomDatabase() {

      abstract fun GetAllHotelsItemDao() : GetAllHotelsItemDao
      abstract fun CustomerBookingDataItemDao() : CustomerBookingDataItemDao
      abstract fun CustomerWishListItemDao() : CustomerWishListItemDao
      abstract fun GetHotelAmenitiesItemDao() : GetHotelAmenitiesItemDao
      abstract fun HotelDescriptionDataItemsDao() : HotelDescriptionDataItemsDao
      abstract fun ListOfTopDealsItemDao() : ListOfTopDealsItemDao
      abstract fun HotelTopDealItemsDao() : HotelTopDealItemsDao
      abstract fun UserHotelDataItemDao() : UserHotelDataItemDao

    companion object{
        @Volatile
        private var INSTANCE : HBDataBase? = null
        fun getDataBase (context: Context): HBDataBase {
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
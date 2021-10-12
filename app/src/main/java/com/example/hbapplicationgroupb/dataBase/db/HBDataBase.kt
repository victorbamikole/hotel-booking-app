package com.example.hbapplicationgroupb.dataBase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hbapplicationgroupb.dataBase.dao.*
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataItem
import com.example.hbapplicationgroupb.model.customerWishList.CustomerWishListItem
import com.example.hbapplicationgroupb.model.hotelAmenities.GetHotelAmenitiesItem
import com.example.hbapplicationgroupb.model.topdealsdata.ListOfTopDealsItem
import com.example.hbapplicationgroupb.model.tophoteldata.HotelTopDealItems
import com.example.hbapplicationgroupb.model.userHotelsData.UserHotelDataItem
import com.example.hbapplicationgroupb.util.HotelTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Database(entities = [
    HotelData::class,
    CustomerBookingDataItem::class,
    CustomerWishListItem::class,
    GetHotelAmenitiesItem::class,
    ListOfTopDealsItem::class,
    HotelTopDealItems::class,
    UserHotelDataItem::class], version = 1, exportSchema = false)

@TypeConverters(HotelTypeConverter::class)
abstract class HBDataBase : RoomDatabase() {
    abstract fun allHotelsDao() : AllHotelDao
    abstract fun CustomerBookingDataItemDao() : CustomerBookingDataItemDao
    abstract fun CustomerWishListItemDao() : CustomerWishListItemDao
    abstract fun GetHotelAmenitiesItemDao() : GetHotelAmenitiesItemDao
    abstract fun ListOfTopDealsItemDao() : ListOfTopDealsItemDao
    abstract fun HotelTopDealItemsDao() : HotelTopDealItemsDao
    abstract fun UserHotelDataItemDao() : UserHotelDataItemDao

    @Module()
    @InstallIn(SingletonComponent::class)
    object DatabaseModule{
        @Volatile
        private var INSTANCE : HBDataBase? = null

        @Singleton
        @Provides
        fun getDataBase(@ApplicationContext context: Context): HBDataBase {
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

//    companion object{
//        @Volatile
//        private var INSTANCE : HBDataBase? = null
//
//        @Singleton
//        @Provides()
//        fun getDataBase (context: Context): HBDataBase {
//            val tempInstance = INSTANCE
//            if(tempInstance!=null){
//                return tempInstance
//            }
//            synchronized(this){
//                val instance = Room.databaseBuilder(context.applicationContext,
//                    HBDataBase::class.java,
//                "app_database").build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}
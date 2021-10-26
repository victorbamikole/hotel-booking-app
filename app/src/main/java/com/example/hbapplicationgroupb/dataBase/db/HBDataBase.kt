package com.example.hbapplicationgroupb.dataBase.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hbapplicationgroupb.dataBase.dao.*
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataItem
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListDataClass
import com.example.hbapplicationgroupb.model.hotelAmenities.GetHotelAmenitiesItem
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData
import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.model.userHotelsData.UserHotelDataItem
import com.example.hbapplicationgroupb.util.HotelTypeConverter


@Database(entities = [
    CustomerBookingDataItem::class,
    GetHotelAmenitiesItem::class,
    TopHotelData::class,
    PageItem::class,
    WishListDataClass::class,
    TopDealAndHotelData::class,
    UserHotelDataItem::class], version = 1, exportSchema = false)

@TypeConverters(HotelTypeConverter::class)
abstract class HBDataBase : RoomDatabase() {
    abstract fun addAllHotelsToDatabase() : AllHotelDao
    abstract fun CustomerBookingDataItemDao() : CustomerBookingDataItemDao
    abstract fun GetHotelAmenitiesItemDao() : GetHotelAmenitiesItemDao
    abstract fun UserHotelDataItemDao() : UserHotelDataItemDao
    abstract fun getAllTopHotelsDao() :  TopHotelDao
    abstract fun wishListDao():WishListDao
//    abstract fun insertTopHotelDao(): TopHotelDao



}
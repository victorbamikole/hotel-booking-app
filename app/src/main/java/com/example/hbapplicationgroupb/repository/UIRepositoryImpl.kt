package com.example.hbapplicationgroupb.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupb.dataBase.db.HBDataBase
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData

import javax.inject.Inject

class UIRepositoryImpl @Inject constructor(
    private val db : HBDataBase
) :  UiRepositoryInterface{

    //Fetch hotel with given id from room database
//    override fun getHotelByIdFromDatabase(hotelId: String): LiveData<HotelData> {
//        return db.addAllHotelsToDatabase().getHotelFromDatabase(hotelId)
//    }

//    override fun addAllHotelsToDb(hotelList: List<TopDealAndHotelData>) {
//        return db.addAllHotelsToDatabase().addAllHotelsToDatabase(hotelList)
//    }

//    override fun insertAllHotelsToDb(hotelList: ArrayList<TopDealAndHotelData>) {
//        return db.addAllHotelsToDatabase().addAllHotelsToDb(hotelList)
//    }


//    override fun getAllHotels(pageSize: Int, currentPage: Int): LiveData<HotelData> {
//        return db.allHotelsDao().addHotelsToDatabase(pageSize,currentPage)
//    }

}
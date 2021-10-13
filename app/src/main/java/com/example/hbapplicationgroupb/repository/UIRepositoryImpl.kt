package com.example.hbapplicationgroupb.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupb.dataBase.db.HBDataBase
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import javax.inject.Inject

class UIRepositoryImpl @Inject constructor(
    private val db : HBDataBase
) :  UiRepositoryInterface{

    //Fetch hotel with given id from room database
    override fun getHotelByIdFromDatabase(hotelId: String): LiveData<HotelData> {
        return db.allHotelsDao().getHotelFromDatabase(hotelId)
    }



}
package com.example.hbapplicationgroupb.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupb.model.allHotels.HotelData


interface UiRepositoryInterface {

    //Fetches hotel with given id from room database
    fun getHotelByIdFromDatabase(hotelId :String) : LiveData<HotelData>

    //add hotel to database

//   suspend fun insertHotelToDatabase(topHotel: TopHotelData)
//
//   fun getAllTopHotels() : LiveData<List<TopHotelData>>


}
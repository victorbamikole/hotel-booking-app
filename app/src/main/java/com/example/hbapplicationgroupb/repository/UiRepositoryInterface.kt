package com.example.hbapplicationgroupb.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UiRepositoryInterface {

    //Fetches hotel with given id from room database
    fun getHotelByIdFromDatabase(hotelId :String) : LiveData<HotelData>
    fun addAllHotelsToDb(hotelList : List<HotelData>)


}
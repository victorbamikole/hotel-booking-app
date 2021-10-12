package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionResponse
import retrofit2.Response


interface ApiRepositoryInterface {

    suspend fun resetForgetPasswordEmail(email: PostForgotPasswordData): Response<ForgotPasswordDataResponse>

    suspend fun getAllHotels() : Response<GetAllHotel>

    suspend fun getHotelDescriptionFromEndPoint(id :String) : Response<HotelDescriptionResponse>

    suspend fun addAllHotelsToRoom(hotelList :List<HotelData>?)

}
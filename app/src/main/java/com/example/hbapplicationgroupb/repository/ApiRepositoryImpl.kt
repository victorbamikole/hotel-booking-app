package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.dataBase.db.HBDataBase
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.api.HotelServices
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionResponse
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val hotelServices :HotelServices,
    private val db :HBDataBase
) :ApiRepositoryInterface {

    override suspend fun resetForgetPasswordEmail(email: PostForgotPasswordData): Response<ForgotPasswordDataResponse> {
        return  hotelServices.resetForgetPasswordEmail(email)
    }

    //Fetch all hotels from api
    override suspend fun getAllHotels(): Response<GetAllHotel> {
        return hotelServices.getAllHotel()
    }

    //    Fetch Hotel Description From End Point
    override suspend fun getHotelDescriptionFromEndPoint(id: String) =
        hotelServices.getHotelDescriptionResponse(id)


    //Add all hotels to Room
    override suspend fun addAllHotelsToRoom(hotelList: List<HotelData>?) {
        if (hotelList != null) {
            db.allHotelsDao().addAllHotelsToDatabase(hotelList)
        }
    }


}
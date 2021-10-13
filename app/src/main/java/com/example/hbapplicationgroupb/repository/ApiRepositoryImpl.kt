package com.example.hbapplicationgroupb.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupb.dataBase.dao.AllHotelDao
import com.example.hbapplicationgroupb.dataBase.dao.GetAllHotelsItemDao
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.api.HotelServices
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.topdealsdata.ListOfTopDealsResponse
import com.example.hbapplicationgroupb.model.topdealsnew.TopDeals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor (
    private val hotelServices: HotelServices
) : ApiRepositoryInterface {
    override suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse> {
        return  hotelServices.resetForgetPasswordEmail(email)
    }

    override suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress): Response<ConfirmEmailAddressResponse> {
        return hotelServices.confirmEmailAddress(emailAndToken)
    }

    override suspend fun getAllHotels(pageSize:Int,currentPage:Int): Response<GetAllHotel> {
        return hotelServices.getAllHotels(pageSize,currentPage)
    }

    override suspend fun getTopDeals(pageSize: Int, pageNumber: Int): Response<TopDeals> {
        return  hotelServices.getListOfTopDealsHotel(pageSize, pageNumber)
    }

//    override suspend fun getToDeals(
//        pageSize: Int,
//        pageNumber: Int
//    ): Response<ListOfTopDealsResponse> {
//        return  hotelServices.getListOfTopDealsHotel(pageSize, pageNumber)
//    }


}
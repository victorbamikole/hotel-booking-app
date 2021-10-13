package com.example.hbapplicationgroupb.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupb.model.Hotels
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.topdealsdata.ListOfTopDealsResponse
import com.example.hbapplicationgroupb.model.topdealsnew.TopDeals
import retrofit2.Response
import retrofit2.http.Body


interface ApiRepositoryInterface {

    suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse>
    suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress):Response<ConfirmEmailAddressResponse>
    suspend fun getAllHotels(pageSize:Int,currentPage:Int): Response<GetAllHotel>
    suspend fun getTopDeals(pageSize: Int, pageNumber:Int): Response<TopDeals>
}
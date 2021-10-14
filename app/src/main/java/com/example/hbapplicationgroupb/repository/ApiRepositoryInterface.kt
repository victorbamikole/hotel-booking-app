package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.topdealsnew.TopDeals
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.tophoteldata.GetListOfTopHotelsResponse
import com.example.hbapplicationgroupb.model.tophotelresponse.AllTopHotels
import retrofit2.Response
import retrofit2.http.Query


interface ApiRepositoryInterface {

    suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse>
    suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress):Response<ConfirmEmailAddressResponse>
    suspend fun getAllHotels(pageSize:Int,currentPage:Int): Response<GetAllHotel>
    suspend fun getTopDeals(pageSize: Int, pageNumber:Int): Response<TopDeals>
    suspend fun resetPassword(password: PostResetPasswordData): Response<ResetPasswordDataResponse>
    suspend fun getTopHotels(PageSize: Int, PageNumber:Int) : Response<AllTopHotels>
}
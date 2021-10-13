package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.Hotels
import com.example.hbapplicationgroupb.model.allHotels.AllHotelsResponse
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import retrofit2.Response
import retrofit2.http.Body


interface ApiToRoomRepositoryInterface {

    suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse>
    suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress):Response<ConfirmEmailAddressResponse>


}
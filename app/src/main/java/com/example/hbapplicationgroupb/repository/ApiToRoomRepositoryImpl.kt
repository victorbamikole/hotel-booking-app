package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.api.HotelServices
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import retrofit2.Response

class ApiToRoomRepositoryImpl (
    private val hotelServices: HotelServices
) :ApiToRoomRepositoryInterface {
    override suspend fun resetForgetPasswordEmail(email: PostForgotPasswordData): Response<ForgotPasswordDataResponse> {
        return  hotelServices.resetForgetPasswordEmail(email)
    }

    override suspend fun resetPassword(password: PostResetPasswordData): Response<ResetPasswordDataResponse> {
        return hotelServices.resetPassword(password)
    }

}
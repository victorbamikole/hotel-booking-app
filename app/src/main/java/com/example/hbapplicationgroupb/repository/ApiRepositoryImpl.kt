package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.api.ApiServices
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiServices :ApiServices
) :ApiRepositoryInterface {

    override suspend fun resetForgetPasswordEmail(email: PostForgotPasswordData): Response<ForgotPasswordDataResponse> {
        return  apiServices.resetForgetPasswordEmail(email)
    }

    override suspend fun getAllHotels(): Response<GetAllHotel> {
        return apiServices.getAllHotel()
    }

}
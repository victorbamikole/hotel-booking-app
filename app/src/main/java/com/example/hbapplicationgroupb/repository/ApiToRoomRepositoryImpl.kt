package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.api.NetworkCall
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import retrofit2.Response

class ApiToRoomRepositoryImpl (
    private val networkCall :NetworkCall
) :ApiToRoomRepositoryInterface {
    override suspend fun resetForgetPasswordEmail(email: PostForgotPasswordData): Response<ForgotPasswordDataResponse> {
        return  networkCall.resetForgetPasswordEmail(email)
    }

}
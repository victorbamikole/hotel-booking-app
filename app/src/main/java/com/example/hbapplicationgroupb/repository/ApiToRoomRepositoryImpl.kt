package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.api.NetworkCall
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import retrofit2.Response

class ApiToRoomRepositoryImpl (
    private val networkCall :NetworkCall
) : ApiToRoomRepositoryInterface {
    override suspend fun resetForgetPasswordEmail(email: PostForgotPasswordData): Response<ForgotPasswordDataResponse> {
        return  networkCall.resetForgetPasswordEmail(email)
    }

    override suspend fun userLoginDetails(userLoginDetails : PostLoginUserData): Response<LoginUserDataResponse> {
        return networkCall.userLoginDetails(userLoginDetails)
    }

}
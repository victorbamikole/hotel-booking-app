package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import retrofit2.Response


interface ApiToRoomRepositoryInterface {

    suspend fun resetForgetPasswordEmail(email: PostForgotPasswordData): Response<ForgotPasswordDataResponse>

    suspend fun userLoginDetails(userLoginDetails : PostLoginUserData) : Response<LoginUserDataResponse>

}
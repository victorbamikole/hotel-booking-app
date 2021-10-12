package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import retrofit2.Response


interface ApiToRoomRepositoryInterface {

    suspend fun resetForgetPasswordEmail(email: PostForgotPasswordData): Response<ForgotPasswordDataResponse>
    suspend fun resetPassword(password: PostResetPasswordData): Response<ResetPasswordDataResponse>

}
package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import retrofit2.Response


interface ApiRepositoryInterface {

    suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse>
    suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress):Response<ConfirmEmailAddressResponse>
    suspend fun resetPassword(password: PostResetPasswordData): Response<ResetPasswordDataResponse>

}
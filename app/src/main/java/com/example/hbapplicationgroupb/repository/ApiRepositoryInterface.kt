package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import retrofit2.Response


interface ApiRepositoryInterface {

    suspend fun resetForgetPasswordEmail(email: String): Response<ResetPasswordDataResponse>
    suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress):Response<ConfirmEmailAddressResponse>
    suspend fun resetPassword(email: PostResetPasswordData): Response<ResetPasswordDataResponse>

}
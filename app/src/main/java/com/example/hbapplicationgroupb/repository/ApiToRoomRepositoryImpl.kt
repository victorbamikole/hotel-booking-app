package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.Hotels
import com.example.hbapplicationgroupb.model.api.NetworkCall
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import retrofit2.Response

class ApiToRoomRepositoryImpl (
    private val networkCall :NetworkCall
) :ApiToRoomRepositoryInterface {
    override suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse> {
        return  networkCall.resetForgetPasswordEmail(email)
    }

    override suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress): Response<ConfirmEmailAddressResponse> {
        return networkCall.confirmEmailAddress(emailAndToken)
    }

}
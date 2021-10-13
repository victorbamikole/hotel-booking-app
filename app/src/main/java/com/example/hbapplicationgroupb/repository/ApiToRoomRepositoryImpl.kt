package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.api.NetworkCall
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import retrofit2.Response
import javax.inject.Inject

class ApiToRoomRepositoryImpl @Inject constructor(
    private val networkCall :NetworkCall
) :ApiToRoomRepositoryInterface {
    override suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse> {
        return  networkCall.resetForgetPasswordEmail(email)
    }

    override suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress): Response<ConfirmEmailAddressResponse> {
        return networkCall.confirmEmailAddress(emailAndToken)
    }


    override suspend fun registerAUser(userData: UserDataResponseItem
    ): Response<UserDataResponseItem> {
        return networkCall.registerAUser(userData)
    }

}
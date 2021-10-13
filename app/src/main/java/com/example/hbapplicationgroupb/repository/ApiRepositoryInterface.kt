package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import retrofit2.Response
import retrofit2.http.Body



interface ApiRepositoryInterface {

    //Register user
    suspend fun registerAUser(userData: UserDataResponseItem) : Response<UserDataResponseItem>
    suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse>
    suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress):Response<ConfirmEmailAddressResponse>

}
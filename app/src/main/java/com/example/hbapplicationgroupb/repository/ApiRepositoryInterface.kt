package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.topdealsnew.TopDeals
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import retrofit2.Response



interface ApiRepositoryInterface {

    //Register user
    suspend fun registerAUser(userData: UserDataResponseItem) : Response<UserDataResponseItem>
    suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse>
    suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress):Response<ConfirmEmailAddressResponse>

    //Fetch hotel Description from api
    suspend fun getHotelDescriptionResponse(id :String) : Response<HotelDescriptionResponse>

    suspend fun getAllHotels(pageSize:Int,currentPage:Int): Response<GetAllHotel>
    suspend fun getTopDeals(pageSize: Int, pageNumber:Int): Response<TopDeals>
    suspend fun resetPassword(password: PostResetPasswordData): Response<ResetPasswordDataResponse>

    suspend fun userLoginDetails(userLoginDetails : PostLoginUserData) : Response<LoginUserDataResponse>

}
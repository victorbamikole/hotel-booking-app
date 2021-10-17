package com.example.hbapplicationgroupb.repository

import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.api.HotelServices
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.topdealsnew.TopDeals
import com.example.hbapplicationgroupb.model.tophotelresponse.AllTopHotels
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor (
    private val hotelServices: HotelServices
) : ApiRepositoryInterface {

    override suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse> {
        return hotelServices.resetForgetPasswordEmail(email)
    }

    override suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress): Response<ConfirmEmailAddressResponse> {
        return hotelServices.confirmEmailAddress(emailAndToken)
    }

    override suspend fun getHotelDescriptionResponse(id: String): Response<HotelDescriptionResponse> {
        return hotelServices.getHotelDescriptionResponse(id)
    }

    override suspend fun userLoginDetails(userLoginDetails: PostLoginUserData): Response<LoginUserDataResponse> {
        return hotelServices.userLoginDetails(userLoginDetails)
    }

    override suspend fun registerAUser(userData: UserDataResponseItem
    ): Response<UserDataResponseItem> {

        return hotelServices.registerAUser(userData)
    }

    override suspend fun getAllHotels(pageSize:Int,currentPage:Int): Response<GetAllHotel> {
        return hotelServices.getAllHotels(pageSize,currentPage)
    }

    override suspend fun getTopDeals(pageSize: Int, pageNumber: Int): Response<TopDeals> {
        return  hotelServices.getListOfTopDealsHotel(pageSize, pageNumber)
    }


    override suspend fun resetPassword(password: PostResetPasswordData): Response<ResetPasswordDataResponse> {
        return hotelServices.resetPassword(password)
    }

    override suspend fun getTopHotels(
        PageSize: Int,
        PageNumber: Int
    ): Response<AllTopHotels> {
        return hotelServices.getTopHotels(PageSize, PageNumber)
    }
}
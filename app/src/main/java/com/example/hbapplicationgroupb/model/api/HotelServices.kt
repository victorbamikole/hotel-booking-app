package com.example.hbapplicationgroupb.model.api

import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataResponse
import com.example.hbapplicationgroupb.model.customerWishList.CustomerWishListResponse
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.hotelAmenities.HotelAmenitiesResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.topdealsdata.ListOfTopDealsResponse
import com.example.hbapplicationgroupb.model.tophoteldata.GetListOfTopHotelsResponse
import com.example.hbapplicationgroupb.model.updateUserPassword.PostUpdateUserPassword
import com.example.hbapplicationgroupb.model.userData.UserDataResponse
import com.example.hbapplicationgroupb.model.userHotelsData.UserHotelDataResponse
import retrofit2.Response
import retrofit2.http.*

interface HotelServices {

    @GET("api/Hotel/all-hotels?")
    suspend fun getAllHotel() : Response<GetAllHotel>

    @GET()
    suspend fun getEachHotelDetails() : Response<UserHotelDataResponse>

    @GET
    suspend fun getTopHotels() : Response<GetListOfTopHotelsResponse>

    @GET
    suspend fun getListOfTopDealsHotel() : Response<ListOfTopDealsResponse>

    //Fetch description for a particular hotel
    @GET("api/Hotel/{hotelId}")
    suspend fun getHotelDescriptionResponse(@Path("hotelId") hotelId :String) : Response<HotelDescriptionResponse>

    @GET
    suspend fun getHotelAmenities() : Response<HotelAmenitiesResponse>

    @POST
    suspend fun bookHotelData() : Response<CustomerBookingDataResponse>

    @POST
    suspend fun addToCustomerWishList() : Response<CustomerWishListResponse>

    @POST
    suspend fun registerAUser() : Response<UserDataResponse>

    @POST("api/Authentication/forgot-password")
    suspend fun resetForgetPasswordEmail(@Query("email") email: PostForgotPasswordData):Response<ForgotPasswordDataResponse>

    @POST("api/Authentication/confirm-email")
    suspend fun confirmEmailAddress(@Body emailAndToken: ConfirmEmailAddress):Response<ConfirmEmailAddressResponse>


    @POST
    suspend fun loginAUser() : Response<LoginUserDataResponse>

    @PATCH
    suspend fun updateLoginDetails() : Response<PostUpdateUserPassword>

}
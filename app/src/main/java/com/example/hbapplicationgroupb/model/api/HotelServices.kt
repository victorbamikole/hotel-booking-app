package com.example.hbapplicationgroupb.model.api

import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.allhotel.AllHotel
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataResponse
import com.example.hbapplicationgroupb.model.customerWishList.CustomerWishListResponse
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.hotelAmenities.HotelAmenitiesResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionResponse
import com.example.hbapplicationgroupb.model.hotelSearchResponse.HotelSearchResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealsAndHotel
import com.example.hbapplicationgroupb.model.tophotelresponse.AllTopHotels
import com.example.hbapplicationgroupb.model.updateUserPassword.PostUpdateUserPassword
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.userHotelsData.UserHotelDataResponse
import retrofit2.Response
import retrofit2.http.*

interface HotelServices {

    @GET("api/Hotel/all-hotels")
    suspend fun getAllHotels(@Query("PageSize") PageSize:Int, @Query("CurrentPage") CurrentPage:Int) : Response<GetAllHotel>

    @GET("api/Hotel/all-hotels")
    suspend fun fetchAllHotels(@Query("PageSize") PageSize:Int,
    @Query("CurrentPage") CurrentPage:Int): Response<AllHotel>

    @GET
    suspend fun getEachHotelDetails() : Response<UserHotelDataResponse>

    @GET("api/Hotel/top-hotels")
    suspend fun getTopHotels(
        @Query("PageSize") PageSize: Int, @Query("PageNumber") PageNumber:Int
    ) : Response<AllTopHotels>
//    suspend fun getTopHotels() : Response<AllTopHotels>
    suspend fun getTopHotels() : Response<TopDealsAndHotel>

    @GET("api/Hotel/top-deals")
    suspend fun getListOfTopDealsHotel() : Response<TopDealsAndHotel>

    //Fetch description for a particular hotel
    @GET("api/Hotel/{hotelId}")
    suspend fun getHotelDescriptionResponse(@Path("hotelId") hotelId :String) : Response<HotelDescriptionResponse>

    /**
     * Search hotel by @location
     * */
    @GET("/api/Hotel/search/{location}")
    suspend fun searchHotelLocation(@Path("location") location: String) : Response<HotelSearchResponse>

    @GET
    suspend fun getHotelAmenities() : Response<HotelAmenitiesResponse>

    @POST
    suspend fun bookHotelData() : Response<CustomerBookingDataResponse>

    @POST
    suspend fun addToCustomerWishList() : Response<CustomerWishListResponse>

    @POST("api/Authentication/register")
    suspend fun registerAUser(@Body userData: UserDataResponseItem) : Response<UserDataResponseItem>

    @POST("api/Authentication/forgot-password")
    suspend fun resetForgetPasswordEmail(@Query("email") email: String):Response<ForgotPasswordDataResponse>

    @POST("api/Authentication/confirm-email")
    suspend fun confirmEmailAddress(@Body emailAndToken: ConfirmEmailAddress):Response<ConfirmEmailAddressResponse>

    @POST("api/Authentication/login")
    suspend fun userLoginDetails(
        @Body userLoginDetails : PostLoginUserData
    ) : Response<LoginUserDataResponse>

    @PATCH("api/Authentication/reset-password")
    suspend fun resetPassword(@Body password: PostResetPasswordData):Response<ResetPasswordDataResponse>

    @POST
    suspend fun loginAUser() : Response<LoginUserDataResponse>

    @PATCH
    suspend fun updateLoginDetails() : Response<PostUpdateUserPassword>

}
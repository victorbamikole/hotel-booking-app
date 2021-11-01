package com.example.hbapplicationgroupb.model.api

import com.example.hbapplicationgroupb.model.addRatings.AddRatingsPost
import com.example.hbapplicationgroupb.model.addRatings.AddRatingsResponse
import com.example.hbapplicationgroupb.model.addReviews.AddReviewsPost
import com.example.hbapplicationgroupb.model.addReviews.AddReviewsResponse
import com.example.hbapplicationgroupb.model.hotelRating.hotelRating.HotelReview
import com.example.hbapplicationgroupb.model.allhotel.AllHotel
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataItem
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataResponse
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.hotelAmenities.HotelAmenitiesResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionResponse
import com.example.hbapplicationgroupb.model.hotelSearchResponse.HotelSearchResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.refreshToken.RefreshTokenResponse
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealsAndHotel
import com.example.hbapplicationgroupb.model.updateUserPassword.PostUpdateUserPassword
import com.example.hbapplicationgroupb.model.userData.UserDataResponse
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.userHotelsData.UserHotelDataResponse
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListResponse
import retrofit2.Response
import retrofit2.http.*

interface HotelServices {

//    @GET("api/Hotel/all-hotels")
//    suspend fun getAllHotels(@Query("PageSize") PageSize:Int, @Query("CurrentPage") CurrentPage:Int) : Response<GetAllHotel>

    @GET("api/Hotel/all-hotels")
    suspend fun fetchAllHotels(@Query("PageSize") PageSize:Int,
    @Query("CurrentPage") CurrentPage:Int): Response<AllHotel>

    @GET
    suspend fun getEachHotelDetails() : Response<UserHotelDataResponse>

    @GET("api/Hotel/top-hotels")
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

    @POST("api/Authentication/register")
    suspend fun registerAUser(@Body userData: UserDataResponseItem) : Response<UserDataResponse>

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

    @PATCH
    suspend fun updateLoginDetails() : Response<PostUpdateUserPassword>

    @GET("/api/Customer/{userId}/bookings")
    suspend fun bookingHistory(
        @Path("userId", encoded = true) userId: String
    ) : Response<CustomerBookingDataItem>

    @GET("/api/Hotel/{hotelId}/reviews")
    suspend fun getHotelReview(@Path("hotelId") hotelId : String) : Response<HotelReview>

    @POST("api/Review/add-reviews")
    suspend fun addReviews(@Body addReview: AddReviewsPost, @Header("Authorization")token: String):Response<AddReviewsResponse>
    @POST("api/Hotel/{hotelId}/add-ratings")
    suspend fun addRatings(@Path("hotelId") hotelId: String, @Body ratings: AddRatingsPost, @Header("Authorization")token: String): Response<AddRatingsResponse>

    @POST("/api/Authentication/refresh-token")
    suspend fun refreshTokenRequest(
        @Query("UserId") userId: String,
        @Query("RefreshToken") refreshToken:String
    ):Response<RefreshTokenResponse>

    @POST("/api/Customer/{hotelId}/add-wishlist")
    suspend fun addCustomerWishToWishList(@Header("Authorization")token: String, @Path("hotelId") hotelId:String):Response<String>

    @DELETE("/api/Customer/{hotelId}/remove-wishlist")
    suspend fun deleteCustomerWishFromWishList(@Header("Authorization")token: String, @Path("hotelId") hotelId:String):Response<String>

    @PATCH("/api/Customer/update-image")
    suspend fun uploadImageToAPI(@Header("Authorization") token:String, @Body uri: String):Response<String>

    @GET("/api/Customer/wishlist")
    suspend fun getAllWishListFromApi(@Header("Authorization")token: String):Response<WishListResponse>
}
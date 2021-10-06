package com.example.hbapplicationgroupb.model.api

import com.example.hbapplicationgroupb.model.TopHotels
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataResponse
import com.example.hbapplicationgroupb.model.customerWishList.CustomerWishListResponse
import com.example.hbapplicationgroupb.model.hotelAmenities.HotelAmenitiesResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.GetListOfHotelDescriptionResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.topdealsdata.ListOfTopDealsResponse
import com.example.hbapplicationgroupb.model.tophoteldata.GetListOfTopHotelsResponse
import com.example.hbapplicationgroupb.model.updateUserPassword.PostUpdateUserPassword
import com.example.hbapplicationgroupb.model.userData.UserDataResponse
import com.example.hbapplicationgroupb.model.userHotelsData.UserHotelDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface NetworkCall {

<<<<<<< HEAD
    @GET("")
    suspend fun getAllHotel() : Response<GetListOfTopHotelsResponse>

    @GET()
    suspend fun getEachHotelDetails() : Response<UserHotelDataResponse>

    @GET
    suspend fun getTopHotels() : Response<GetListOfTopHotelsResponse>

    @GET
    suspend fun getListOfTopDealsHotel() : Response<ListOfTopDealsResponse>

    @GET
    suspend fun getListOfHotelDescription() : Response<GetListOfHotelDescriptionResponse>

    @GET
    suspend fun getHotelAmenities() : Response<HotelAmenitiesResponse>

    @POST
    suspend fun bookHotelData() : Response<CustomerBookingDataResponse>

    @POST
    suspend fun addToCustomerWishList() : Response<CustomerWishListResponse>

    @POST
    suspend fun registerAUser() : Response<UserDataResponse>

    @POST
    suspend fun loginAUser() : Response<LoginUserDataResponse>

    @PATCH
    suspend fun updateLoginDetails() : Response<PostUpdateUserPassword>

=======
>>>>>>> 5b76a2bf4deabe8736aa43129aced88c29714da0

}
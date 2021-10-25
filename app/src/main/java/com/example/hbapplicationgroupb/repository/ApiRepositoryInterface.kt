package com.example.hbapplicationgroupb.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupb.model.allhotel.AllHotel
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionResponse
import com.example.hbapplicationgroupb.model.hotelSearchResponse.HotelSearchResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealsAndHotel
import com.example.hbapplicationgroupb.model.tophotelresponse.AllTopHotels

import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.model.userData.UserDataResponse

import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.util.resource.Resource
import kotlinx.coroutines.flow.Flow

import retrofit2.Response


interface ApiRepositoryInterface {

    //Register user
    suspend fun registerAUser(userData: UserDataResponseItem) : Response<UserDataResponse>
    suspend fun resetForgetPasswordEmail(email: String): Response<ForgotPasswordDataResponse>
    suspend fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress):Response<ConfirmEmailAddressResponse>

    //Fetch hotel Description from api
    suspend fun getHotelDescriptionResponse(id :String) : Response<HotelDescriptionResponse>

//    suspend fun getAllHotels(pageSize:Int,currentPage:Int): Response<GetAllHotel>
    suspend fun fetchAllHotels(pageSize:Int,currentPage:Int): Response<AllHotel>

    suspend fun getTopDeals(): Response<TopDealsAndHotel>
    suspend fun resetPassword(password: PostResetPasswordData): Response<ResetPasswordDataResponse>
    suspend fun getTopHotels() : Response<TopDealsAndHotel>
    suspend fun userLoginDetails(userLoginDetails : PostLoginUserData) : Response<LoginUserDataResponse>

    suspend fun searchHotelLocation(location: String) : Response<HotelSearchResponse>

    //fetching data into Database
//    suspend fun AddAllHotelsToDb(allHotels: ArrayList<HotelData>): Response<GetAllHotel>


    suspend fun insertHotelToDatabase(topHotel: List<TopHotelData>)

    fun getAllTopHotels() : LiveData<List<TopHotelData>>
    fun getAllHotelsFomApiToDB(): Flow<Resource<List<PageItem>>>

}
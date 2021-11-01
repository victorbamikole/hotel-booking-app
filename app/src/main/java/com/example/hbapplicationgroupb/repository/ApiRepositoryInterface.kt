package com.example.hbapplicationgroupb.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupb.model.addRatings.AddRatingsPost
import com.example.hbapplicationgroupb.model.addRatings.AddRatingsResponse
import com.example.hbapplicationgroupb.model.addReviews.AddReviewsPost
import com.example.hbapplicationgroupb.model.addReviews.AddReviewsResponse
import com.example.hbapplicationgroupb.model.hotelRating.hotelRating.HotelReview
import com.example.hbapplicationgroupb.model.allhotel.AllHotel
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataItem
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListDataClass
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionResponse
import com.example.hbapplicationgroupb.model.hotelSearchResponse.HotelSearchResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.refreshToken.RefreshTokenResponse
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealsAndHotel

import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.model.userData.UserDataResponse

import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListResponse
import com.example.hbapplicationgroupb.util.resource.Resource
import kotlinx.coroutines.flow.Flow

import retrofit2.Response
import retrofit2.http.*


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

    suspend fun addReviews(addReview: AddReviewsPost, token: String): Response<AddReviewsResponse>

    suspend fun addRating(hotelId: String, rating: AddRatingsPost, token: String): Response<AddRatingsResponse>

    //fetching data into Database
//    suspend fun AddAllHotelsToDb(allHotels: ArrayList<HotelData>): Response<GetAllHotel>


    suspend fun insertHotelToDatabase(topHotel: List<TopHotelData>)

    //wish list query
    fun getAllWishList(token:String):LiveData<List<WishListDataClass>>
    suspend fun insertWishToDataBase(item:WishListDataClass)
    suspend fun deleteWishFromDataBase(wishId:WishListDataClass)


    fun getAllTopHotels() : LiveData<List<TopHotelData>>
    fun getAllHotelsFomApiToDB(): Flow<Resource<List<PageItem>>>


    suspend fun bookingHistory(userId : String) : Response<CustomerBookingDataItem>

    suspend fun getHotelReview(id : String) : Response<HotelReview>

    //refresh token
    suspend fun refreshTokenRequest(userId: String, refreshToken:String):
            Response<RefreshTokenResponse>


    suspend fun addCustomerWishToWishList(token: String,hotelId:String):Response<String>

    suspend fun deleteCustomerWishFromWishList(token: String, hotelId:String):Response<String>
    suspend fun uploadImageToAPI(token:String,uri: String):Response<String>

    suspend fun getAllWishListFromApi(token: String):Response<WishListResponse>

}
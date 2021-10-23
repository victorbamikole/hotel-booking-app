package com.example.hbapplicationgroupb.repository

import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.example.hbapplicationgroupb.dataBase.db.HBDataBase
import com.example.hbapplicationgroupb.model.allhotel.AllHotel
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.model.api.HotelServices
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionResponse
import com.example.hbapplicationgroupb.model.hotelSearchResponse.HotelSearchResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealsAndHotel
import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.model.userData.UserDataResponse
import com.example.hbapplicationgroupb.util.resource.networkBoundResource
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor (
    private val hotelServices: HotelServices,
    private val db : HBDataBase
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

    override suspend fun searchHotelLocation(location: String): Response<HotelSearchResponse> {
        return hotelServices.searchHotelLocation(location)
    }

    override suspend fun registerAUser(userData: UserDataResponseItem
    ): Response<UserDataResponse> {

        return hotelServices.registerAUser(userData)
    }

//    override suspend fun getAllHotels(pageSize:Int,currentPage:Int): Response<GetAllHotel> {
//        return hotelServices.getAllHotels(pageSize,currentPage)
//    }

    override fun getAllHotelsFomApiToDB() = networkBoundResource(
        dbQueryFunc = {
            db.addAllHotelsToDatabase().getAllHotelsFromRoom()
        },
        fetchDataToBeSavedToDbFunc = {
            delay(2000)
            hotelServices.fetchAllHotels(10,1)
        },
        saveFetchedResultToDb = {
                db.withTransaction {
                    db.addAllHotelsToDatabase().deleteAllFromDb()
                    db.addAllHotelsToDatabase().addAllHotelsToDb(it.data.pageItems)
                }
        },
//        shouldFetch = {}
    )

    override suspend fun fetchAllHotels(pageSize: Int, currentPage: Int): AllHotel {
        return hotelServices.fetchAllHotels(pageSize,currentPage)

    }

    override suspend fun getTopDeals(): Response<TopDealsAndHotel> {
        return  hotelServices.getListOfTopDealsHotel()
    }


    override suspend fun resetPassword(password: PostResetPasswordData): Response<ResetPasswordDataResponse> {
        return hotelServices.resetPassword(password)
    }

    override suspend fun getTopHotels(): Response<TopDealsAndHotel> {
        return hotelServices.getTopHotels()
    }


    override suspend fun insertHotelToDatabase(topHotel: List<TopHotelData>) = db.getAllTopHotelsDao().insertTopHotel(topHotel)

    override fun getAllTopHotels(): LiveData<List<TopHotelData>> = db.getAllTopHotelsDao().getAllTopHotels()


}
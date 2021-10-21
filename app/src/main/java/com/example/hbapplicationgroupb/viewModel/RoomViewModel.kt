package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.dataBase.db.HBDataBase
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.allhotel.AllHotel
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionData
import com.example.hbapplicationgroupb.model.hotelSearchResponse.HotelSearchResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.tophotelresponse.AllTopHotels
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealsAndHotel
import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.model.userData.UserDataResponse
import com.example.hbapplicationgroupb.repository.ApiRepositoryInterface
import com.example.hbapplicationgroupb.util.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
   private val apiRepository : ApiRepositoryInterface,private val db: HBDataBase
) : ViewModel() {

   //User Added
  private val _newUser: MutableLiveData<UserDataResponse> = MutableLiveData()
   val newUser:LiveData<UserDataResponse> = _newUser

   //Hotel description livedata
   private val _hotelDescription: MutableLiveData<HotelDescriptionData> = MutableLiveData()
   val hotelDescription: LiveData<HotelDescriptionData> get() = _hotelDescription


   private val _allHotelsList = MutableLiveData<GetAllHotel?>()
   val allHotelsList: LiveData<GetAllHotel?> = _allHotelsList

   private val _allTopDeals = MutableLiveData<TopDealsAndHotel?>()
   val allTopDeals: LiveData<TopDealsAndHotel?> = _allTopDeals

   private var _forgotPasswordData: MutableLiveData<ForgotPasswordDataResponse> = MutableLiveData()
   val forgotPasswordData: LiveData<ForgotPasswordDataResponse> = _forgotPasswordData

   private var _userLoginDetails: MutableLiveData<LoginUserDataResponse> = MutableLiveData()
   val userLoginDetails: LiveData<LoginUserDataResponse> = _userLoginDetails

   private var _resetPasswordData: MutableLiveData<ResetPasswordDataResponse> = MutableLiveData()
   val resetPasswordData: LiveData<ResetPasswordDataResponse> = _resetPasswordData

   //get top hotels
   private val _topHotel : MutableLiveData<TopDealsAndHotel> = MutableLiveData()
   val topHotels : LiveData<TopDealsAndHotel> = _topHotel


   private var _confirmEmailAddress: MutableLiveData<ConfirmEmailAddressResponse> = MutableLiveData()
   val confirmEmailAddress: LiveData<ConfirmEmailAddressResponse> = _confirmEmailAddress

   private var _hotelLocation: MutableLiveData<HotelSearchResponse> = MutableLiveData()
   val hotelLocation: MutableLiveData<HotelSearchResponse> = _hotelLocation
   private var _fetchAllHotelResponse: MutableLiveData<Response<AllHotel>> = MutableLiveData()
   val fetchAllHotelResponse : LiveData<Response<AllHotel>> = _fetchAllHotelResponse


   init {
      getTopHotels()
      getAllHotels()
   }



         fun registerUser(userData: UserDataResponseItem) {
            viewModelScope.launch {
               try {
                  val response = apiRepository.registerAUser(userData)
                  if (response.isSuccessful) {
                     val responseBody = response.body()
                          _newUser.postValue(responseBody)
                  }else{
                      _newUser.postValue(response.body())
                  }

               } catch (e: Exception) {
                  e.printStackTrace()
               }
            }
         }


         fun sendForgetPasswordEmailToApi(email: String) {
            viewModelScope.launch(Dispatchers.IO) {
               try {
                  val response = apiRepository.resetForgetPasswordEmail(email)
                  if (response.isSuccessful) {
                     val responseBody = response.body()
                     _forgotPasswordData.postValue(responseBody)
                  } else {
                     _forgotPasswordData.postValue(null)
                  }
               } catch (e: Exception) {
                  e.printStackTrace()
               }
            }
         }

         fun sendUserLoginDetailsToApi(userLoginDetails: PostLoginUserData) {
            viewModelScope.launch {
               try {
                  val response = apiRepository.userLoginDetails(userLoginDetails)
                  if (response.isSuccessful) {
                     _userLoginDetails.postValue(response.body())
                  } else {
                     _userLoginDetails.postValue(null)
                  }
               } catch (e: Exception) {
                  e.printStackTrace()
               }

            }
         }

         fun confirmEmailAddress(emailAndToken: ConfirmEmailAddress) {
            viewModelScope.launch(Dispatchers.IO) {
               try {
                  val response = apiRepository.confirmEmailAddress(emailAndToken)
                  if (response.isSuccessful) {
                     val responseBody = response.body()
                     _confirmEmailAddress.postValue(responseBody)
                  }
               } catch (e: Exception) {
                  e.printStackTrace()
               }
            }
         }

         fun getAllHotels(pageSize: Int, currentPage: Int) {

            viewModelScope.launch {
               try {
                  val response = apiRepository.getAllHotels(pageSize, currentPage)
                  if (response.isSuccessful) {
                     _allHotelsList.postValue(response.body())
                  } else {
                     _allHotelsList.postValue(null)
                  }
               } catch (e: Exception) {
                  e.printStackTrace()
               }

            }
         }


         fun sendNewPasswordToAPI(password: PostResetPasswordData) {
            viewModelScope.launch(Dispatchers.IO) {
               try {
                  val response = apiRepository.resetPassword(password)
                  if (response.isSuccessful) {
                     val responseBody = response.body()
                     _resetPasswordData.postValue(responseBody)
                  } else {
                     _resetPasswordData.postValue(null)
                  }
               } catch (e: Exception) {
                  e.printStackTrace()
               }
            }
         }


         fun getTopDeals() {

            viewModelScope.launch {
               try {
                  val response = apiRepository.getTopDeals()
                  if (response.isSuccessful) {
                     _allTopDeals.postValue(response.body())
                  } else {
                     _allTopDeals.postValue(null)
                  }
               } catch (e: Exception) {
                  e.printStackTrace()
               }

            }
         }


   fun getTopHotels() {
      viewModelScope.launch {
         try {
            val response = apiRepository.getTopHotels()
            if (response.isSuccessful) {
               _topHotel.postValue(response.body())
            } else {
               _topHotel.postValue(null)
            }
         } catch (e: Exception) {
            e.printStackTrace()

         }
      }
   }

   fun getAllHotels(){
      viewModelScope.launch {
         try {
             val response = apiRepository.fetchAllHotels(10,1)
            if (response.isSuccessful){
               _fetchAllHotelResponse.postValue(response)
            }else{
               _fetchAllHotelResponse.postValue(null)
            }
         }catch (e:Exception){
            e.printStackTrace()
         }
      }
   }


      //Fetch hotel description from api
      fun getHotelDescription(id: String) {
         viewModelScope.launch(Dispatchers.IO) {
            try {
               val response = apiRepository.getHotelDescriptionResponse(id)
               if (response.isSuccessful) {
                  _hotelDescription.postValue(response.body()?.data)
               } else {
                  _hotelDescription.postValue(null)
               }
            } catch (e: Exception) {
               e.printStackTrace()
            }
         }
      }

   private fun handleTopHotelResponse(response: Response<AllTopHotels>): Resource<AllTopHotels> {

      if (response.isSuccessful) {
         response.body()?.let { result ->
            return Resource.Success(result)
         }
      }
      return Resource.Error(response.message())
   }



   fun saveTopHotels(topHotelData: List<TopHotelData>) = viewModelScope.launch {
         apiRepository.insertHotelToDatabase(topHotelData)
      }

      fun getAllTopHotel() = viewModelScope.launch {
         apiRepository.getAllTopHotels()
      }

   //fetch hotel location
   fun searchHotelLocation(location: String) {
      viewModelScope.launch(Dispatchers.IO){
         try {
             val response = apiRepository.searchHotelLocation(location)
            if (response.isSuccessful){
               val responseBody = response.body()
               _hotelLocation.postValue(responseBody)
            }else{
               _hotelLocation.postValue(null)
            }
         }catch (e:Exception){
            e.printStackTrace()
         }
      }
   }
}





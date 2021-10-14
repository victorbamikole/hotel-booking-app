package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionData
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.topdealsdata.ListOfTopDealsResponse
import com.example.hbapplicationgroupb.model.topdealsnew.TopDeals
import com.example.hbapplicationgroupb.repository.ApiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
   private val apiRepository : ApiRepositoryInterface
) : ViewModel() {

   //User Added
   val newUser: MutableLiveData<Response<UserDataResponseItem>> = MutableLiveData()

   //Hotel description livedata
   private val _hotelDescription = MutableLiveData<HotelDescriptionData>()
   val hotelDescription: LiveData<HotelDescriptionData> get() = _hotelDescription

   //Hotel Room Types
   val hotelRooms = hotelDescription.value?.hotelDescriptionRoomTypes

   private val _allHotelsList = MutableLiveData<GetAllHotel?>()
   val allHotelsList:LiveData<GetAllHotel?> = _allHotelsList

   private val _allTopDeals = MutableLiveData<TopDeals?>()
   val allTopDeals:LiveData<TopDeals?> = _allTopDeals

   private var _forgotPasswordData: MutableLiveData<ForgotPasswordDataResponse> = MutableLiveData()
   val forgotPasswordData: LiveData<ForgotPasswordDataResponse> = _forgotPasswordData

   private var _resetPasswordData: MutableLiveData<ResetPasswordDataResponse> = MutableLiveData()
   val resetPasswordData: LiveData<ResetPasswordDataResponse> = _resetPasswordData




   fun registerUser(userData : UserDataResponseItem){
      viewModelScope.launch {
         try {
            val response = apiRepository.registerAUser(userData)
            if(response.isSuccessful){
               newUser.value = response
            } else{
               newUser.value = null
            }

         } catch (e: Exception){
            e.printStackTrace()
         }
      }
   }



      var _confirmEmailAddress: MutableLiveData<ConfirmEmailAddressResponse> = MutableLiveData()
      val confirmEmailAddress: LiveData<ConfirmEmailAddressResponse> = _confirmEmailAddress

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


   fun getAllHotels(pageSize:Int,currentPage:Int){

      viewModelScope.launch {
         try{
            val response = apiRepository.getAllHotels(pageSize,currentPage)
            if (response.isSuccessful){
               _allHotelsList.postValue(response.body())
            }else{
               _allHotelsList.postValue(null)
            }
         }catch (e:Exception){
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
               } else {
                  _confirmEmailAddress.postValue(null)
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



   fun getTopDeals(pageSize:Int,pageNumber:Int){

      viewModelScope.launch {
         try{
            val response = apiRepository.getTopDeals(pageSize,pageNumber)
            if (response.isSuccessful){
               _allTopDeals.postValue(response.body())
            }else{
               _allTopDeals.postValue(null)
            }
         }catch (e:Exception){
            e.printStackTrace()
         }

      }
   }

   }





package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionData
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.repository.ApiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
   private val apiRepository : ApiRepositoryInterface
) : ViewModel() {

   //Hotel description livedata
   private val _hotelDescription = MutableLiveData<HotelDescriptionData>()
   val hotelDescription: LiveData<HotelDescriptionData> get() = _hotelDescription

   //Hotel Room Types
   val hotelRooms = hotelDescription.value?.hotelDescriptionRoomTypes

   private val allHotelsList = MutableLiveData<List<HotelData>>()

   private var _forgotPasswordData: MutableLiveData<ForgotPasswordDataResponse> = MutableLiveData()
   val forgotPasswordData: LiveData<ForgotPasswordDataResponse> = _forgotPasswordData

   private var _userLoginDetails : MutableLiveData<LoginUserDataResponse> = MutableLiveData()
   val userLoginDetails : LiveData<LoginUserDataResponse> = _userLoginDetails

   private var _resetPasswordData: MutableLiveData<ResetPasswordDataResponse> = MutableLiveData()
   val resetPasswordData: LiveData<ResetPasswordDataResponse> = _resetPasswordData

   private var _confirmEmailAddress: MutableLiveData<ConfirmEmailAddressResponse> = MutableLiveData()
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

   fun sendUserLoginDetailsToApi(userLoginDetails : PostLoginUserData){
      viewModelScope.launch{
         try {
            val response = apiRepository.userLoginDetails(userLoginDetails)
            if (response.isSuccessful){
               _userLoginDetails.postValue(response.body())
            }
         }
         catch (e:Exception){
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
            else {
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
            }
            else {
               _resetPasswordData.postValue(null)
            }
         }
         catch (e: Exception) {
            e.printStackTrace()
         }
      }
   }
}

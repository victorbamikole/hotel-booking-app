package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
<<<<<<< HEAD
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.repository.ApiToRoomRepositoryInterface
=======
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionData
import com.example.hbapplicationgroupb.repository.ApiRepositoryInterface
>>>>>>> a383825c4d155602e1aed08b5d5d03c92eeb5e92
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
   private val apiRepository : ApiRepositoryInterface
) : ViewModel(){

   //Hotel description livedata
   private val _hotelDescription =  MutableLiveData<HotelDescriptionData>()
   val hotelDescription : LiveData<HotelDescriptionData> get() = _hotelDescription

   //Hotel Room Types
   val hotelRooms = hotelDescription.value?.hotelDescriptionRoomTypes

   private val allHotelsList = MutableLiveData<List<HotelData>>()

   private var _forgotPasswordData: MutableLiveData<ForgotPasswordDataResponse> = MutableLiveData()
   val forgotPasswordData: LiveData<ForgotPasswordDataResponse> = _forgotPasswordData

<<<<<<< HEAD
   private var _resetPasswordData: MutableLiveData<ResetPasswordDataResponse> = MutableLiveData()
   val resetPasswordData: LiveData<ResetPasswordDataResponse> = _resetPasswordData

         fun sendForgetPasswordEmailToApi(email: PostForgotPasswordData){
=======

   private var _confirmEmailAddress: MutableLiveData<ConfirmEmailAddressResponse> = MutableLiveData()
   val confirmEmailAddress: LiveData<ConfirmEmailAddressResponse> = _confirmEmailAddress

         fun sendForgetPasswordEmailToApi(email: String){
>>>>>>> a383825c4d155602e1aed08b5d5d03c92eeb5e92
            viewModelScope.launch(Dispatchers.IO){
               try {
                  val response = apiRepository.resetForgetPasswordEmail(email)
                  if(response.isSuccessful){
                  val responseBody = response.body()
                     _forgotPasswordData.postValue(responseBody)
                  }else{
                     _forgotPasswordData.postValue(null)
                  }
               }catch (e:Exception){
                  e.printStackTrace()

               }

            }
         }

   //sending password to database
   fun sendNewPasswordToAPI(password: PostResetPasswordData) {
      viewModelScope.launch(Dispatchers.IO){
         try {
             val response = apiToRoomRepository.resetPassword(password)
            if (response.isSuccessful){
               val responseBody = response.body()
               _resetPasswordData.postValue(responseBody)
            }else {
               _resetPasswordData.postValue(null)
            }
         }catch (e:Exception){
            e.printStackTrace()
         }
      }
   }



   fun confirmEmailAddress(emailAndToken:ConfirmEmailAddress){
      viewModelScope.launch(Dispatchers.IO){
         try {
            val response = apiRepository.confirmEmailAddress(emailAndToken)
            if(response.isSuccessful){
               val responseBody = response.body()
               _confirmEmailAddress.postValue(responseBody)
            }else{
               _confirmEmailAddress.postValue(null)
            }
         }catch (e:Exception){
            e.printStackTrace()

         }

      }
   }


}
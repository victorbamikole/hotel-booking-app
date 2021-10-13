package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.repository.ApiToRoomRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
   private val apiToRoomRepository : ApiToRoomRepositoryInterface
) : ViewModel(){
   private var _forgotPasswordData: MutableLiveData<ForgotPasswordDataResponse> = MutableLiveData()
   val forgotPasswordData: LiveData<ForgotPasswordDataResponse> = _forgotPasswordData

   private var _userLoginDetails : MutableLiveData<Response<LoginUserDataResponse>> = MutableLiveData()
   val userLoginDetails : LiveData<Response<LoginUserDataResponse>> = _userLoginDetails



   fun sendForgetPasswordEmailToApi(email: PostForgotPasswordData){
      viewModelScope.launch(Dispatchers.IO){
         try {
            val response = apiToRoomRepository.resetForgetPasswordEmail(email)
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

   fun sendUserLoginDetailsToApi(userLoginDetails : PostLoginUserData){
      viewModelScope.launch {
         val response = apiToRoomRepository.userLoginDetails(userLoginDetails)
         _userLoginDetails.value = response

      }
   }




}
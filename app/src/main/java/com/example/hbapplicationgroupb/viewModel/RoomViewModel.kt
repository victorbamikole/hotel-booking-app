package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionData
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionRoomType
import com.example.hbapplicationgroupb.repository.ApiRepositoryInterface
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


   private val allHotelsList = MutableLiveData<List<HotelData>>()

   private var _forgotPasswordData: MutableLiveData<ForgotPasswordDataResponse> = MutableLiveData()
   val forgotPasswordData: LiveData<ForgotPasswordDataResponse> = _forgotPasswordData


   private var _confirmEmailAddress: MutableLiveData<ConfirmEmailAddressResponse> = MutableLiveData()
   val confirmEmailAddress: LiveData<ConfirmEmailAddressResponse> = _confirmEmailAddress


         fun sendForgetPasswordEmailToApi(email: String){
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

   //Fetch hotel description from api
   fun getHotelDescription(id :String) {
      viewModelScope.launch (Dispatchers.IO){
         try {
            val response = apiRepository.getHotelDescriptionResponse(id)
            if (response.isSuccessful){
               _hotelDescription.postValue(response.body()?.data)
            } else{
               //DO SOMETHING
            }
         } catch (e :Exception){
            e.printStackTrace()
         }
      }
   }


}
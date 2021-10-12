package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.PostForgotPasswordData
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

   //Hotel Room Types
   val hotelRooms = hotelDescription.value?.hotelDescriptionRoomTypes

   private val allHotelsList = MutableLiveData<List<HotelData>>()

   private var _forgotPasswordData: MutableLiveData<ForgotPasswordDataResponse> = MutableLiveData()
   val forgotPasswordData: LiveData<ForgotPasswordDataResponse> = _forgotPasswordData


   init {
      getAllHotelsFromApi()

      addAllHotelsToRoom()
   }

         fun sendForgetPasswordEmailToApi(email: PostForgotPasswordData){
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

   //Fetch all the Hotels from the Api
   private fun getAllHotelsFromApi(){
      viewModelScope.launch {
         val response = apiRepository.getAllHotels()
         if (response.isSuccessful){
            allHotelsList.postValue(response.body()?.data)
         }
      }
   }

   //Save hotels from Api into Room Database
   private fun addAllHotelsToRoom(){
      viewModelScope.launch (Dispatchers.IO){
         apiRepository.addAllHotelsToRoom(allHotelsList.value)
      }
   }

   //Get hotel with given id from end point
   fun getHotelDescription(hotelId :String){

      try {
         viewModelScope.launch (Dispatchers.IO){
            val response = apiRepository.getHotelDescriptionFromEndPoint(hotelId)
            if (response.isSuccessful){
               _hotelDescription.postValue(response.body()?.hotelDescriptionData)
            }
         }
      }catch (e: Exception){
         e.printStackTrace()
      }

   }


}
package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionData
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.topdealsnew.TopDeals
import com.example.hbapplicationgroupb.model.tophoteldata.HotelTopDealItems
import com.example.hbapplicationgroupb.model.tophotelresponse.AllTopHotels
import com.example.hbapplicationgroupb.model.tophotelresponse.Data
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


   /**Live data for Adult*/
   private var _numAdults : MutableLiveData<Int> = MutableLiveData(0)
   val numAdults : LiveData<Int> = _numAdults


   /**Live data for Teens*/
   private var _numTeens : MutableLiveData<Int> = MutableLiveData(0)
   val numTeens : LiveData<Int> =  _numTeens


   /**Live data for Children*/
   private var _numChildren : MutableLiveData<Int> = MutableLiveData(0)
   val numChildren: LiveData<Int> =  _numChildren


   /**Live data for Infants*/
   private var _numInfant:MutableLiveData<Int> = MutableLiveData(0)
   val numInfant:LiveData<Int> = _numInfant




   /**Update Adult count*/
   fun addToAdultCount () {
      _numAdults.value = _numAdults.value!!.plus(1)
   }

   fun subtractFromAdultCount () {
      _numAdults.value = _numAdults.value!!.minus(1)
   }



   /**Update Teens count*/
   fun addToTeensCount () {
      _numTeens.value = _numTeens.value!!.plus(1)
   }

   fun subtractFromTeensCount () {
      _numTeens.value = _numTeens.value!!.minus(1)
   }


   /**Update Children count*/
   fun addToChildrenCount () {
      _numChildren.value = _numChildren.value!!.plus(1)
   }

   fun subtractFromChildrenCount () {
      _numChildren.value = _numChildren.value!!.minus(1)
   }


   /**Update Infant count*/
   fun addToInfantCount (){
      _numInfant.value = _numInfant.value!!.plus(1)
   }
   fun subtractFromInfantCount (){
      _numInfant.value = _numInfant.value!!.minus(1)
   }


   //User Added
   val newUser: MutableLiveData<Response<UserDataResponseItem>> = MutableLiveData()

   //Hotel description livedata
   private val _hotelDescription: MutableLiveData<HotelDescriptionData> = MutableLiveData()
   val hotelDescription: LiveData<HotelDescriptionData> get() = _hotelDescription


   private val _allHotelsList = MutableLiveData<GetAllHotel?>()
   val allHotelsList: LiveData<GetAllHotel?> = _allHotelsList

   private val _allTopDeals = MutableLiveData<TopDeals?>()
   val allTopDeals: LiveData<TopDeals?> = _allTopDeals

   private var _forgotPasswordData: MutableLiveData<ForgotPasswordDataResponse> = MutableLiveData()
   val forgotPasswordData: LiveData<ForgotPasswordDataResponse> = _forgotPasswordData

   private var _userLoginDetails: MutableLiveData<LoginUserDataResponse> = MutableLiveData()
   val userLoginDetails: LiveData<LoginUserDataResponse> = _userLoginDetails

   private var _resetPasswordData: MutableLiveData<ResetPasswordDataResponse> = MutableLiveData()
   val resetPasswordData: LiveData<ResetPasswordDataResponse> = _resetPasswordData

   //get top hotels
   private val _topHotel : MutableLiveData<AllTopHotels> = MutableLiveData()
   val topHotels : LiveData<AllTopHotels> = _topHotel

   private var _confirmEmailAddress: MutableLiveData<ConfirmEmailAddressResponse> = MutableLiveData()
   val confirmEmailAddress: LiveData<ConfirmEmailAddressResponse> = _confirmEmailAddress




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


   fun getTopDeals(pageSize: Int, pageNumber: Int) {

      viewModelScope.launch {
         try {
            val response = apiRepository.getTopDeals(pageSize, pageNumber)
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

   fun getTopHotels(PageSize: Int, PageNumber: Int){
      viewModelScope.launch {
         try {
             val response = apiRepository.getTopHotels(PageSize,PageNumber)
            if (response.isSuccessful){
               _topHotel.postValue(response.body())
            }else{
               _topHotel.postValue(null)
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
}





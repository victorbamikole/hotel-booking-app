package com.example.hbapplicationgroupb.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.hbapplicationgroupb.model.addRatings.AddRatingsPost
import com.example.hbapplicationgroupb.model.addRatings.AddRatingsResponse
import com.example.hbapplicationgroupb.model.addReviews.AddReviewsPost
import com.example.hbapplicationgroupb.model.addReviews.AddReviewsResponse
import com.example.hbapplicationgroupb.model.hotelRating.hotelRating.PageItems
import com.example.hbapplicationgroupb.model.allhotel.AllHotel
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataItem
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddressResponse
import com.example.hbapplicationgroupb.model.forgotPasswordData.ForgotPasswordDataResponse
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionData
import com.example.hbapplicationgroupb.model.hotelRating.hotelRating.HotelRating
import com.example.hbapplicationgroupb.model.hotelSearchResponse.HotelSearchResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.refreshToken.RefreshTokenResponse
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealsAndHotel
import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.model.updateUserData.PostUpdateUserData
import com.example.hbapplicationgroupb.model.updateUserData.UpdateUserDataResponse
import com.example.hbapplicationgroupb.model.userData.UserDataResponse
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.userData.UserProfile
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListDataClass
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListResponse
import com.example.hbapplicationgroupb.repository.ApiRepositoryInterface
import com.example.hbapplicationgroupb.util.resource.ApiCallNetworkResource
import com.example.hbapplicationgroupb.util.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.Body
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val apiRepository : ApiRepositoryInterface
) : ViewModel() {

    private var _getAllWishListFromAoi: MutableLiveData<WishListResponse> = MutableLiveData()
    val getAllWishListFromApi:LiveData<WishListResponse> = _getAllWishListFromAoi

    private var _refreshToken: MutableLiveData<RefreshTokenResponse> = MutableLiveData()
    val refreshToken:LiveData<RefreshTokenResponse> = _refreshToken

    private var _addCustomerWish: MutableLiveData<String> = MutableLiveData()
    val addCustomerWish:LiveData<String> = _addCustomerWish

    private var _deleteCustomerWish: MutableLiveData<String> = MutableLiveData()
    val deleteCustomerWish:LiveData<String> = _deleteCustomerWish

    private var _uploadImage: MutableLiveData<String> = MutableLiveData()
    val uploadImage:LiveData<String> = _uploadImage

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
    private val _newUser: MutableLiveData<ApiCallNetworkResource<UserDataResponse?>> = MutableLiveData()
    val newUser:LiveData<ApiCallNetworkResource<UserDataResponse?>> = _newUser

    //Hotel description livedata
    private val _hotelDescription: MutableLiveData<HotelDescriptionData> = MutableLiveData()
    val hotelDescription: LiveData<HotelDescriptionData> get() = _hotelDescription

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

    private var _bookingHistory : MutableLiveData<CustomerBookingDataItem> = MutableLiveData()
    val bookingHistory : LiveData<CustomerBookingDataItem> = _bookingHistory

    private var _hotelReview : MutableLiveData<Resource<List<PageItems>>> = MutableLiveData<Resource<List<PageItems>>>()
    val hotelReview : LiveData<Resource<List<PageItems>>> = _hotelReview

    private var _updatedDetails : MutableLiveData<ApiCallNetworkResource<UpdateUserDataResponse>> = MutableLiveData()
    val updatedDetails : LiveData<ApiCallNetworkResource<UpdateUserDataResponse>> = _updatedDetails

    private var _addReviews: MutableLiveData<AddReviewsResponse> = MutableLiveData()
    val addReviews: LiveData<AddReviewsResponse> = _addReviews

    private var _addRatings: MutableLiveData<AddRatingsResponse> = MutableLiveData()
    val addRatings: LiveData<AddRatingsResponse> = _addRatings

    private var _userProfile : MutableLiveData<UserProfile> = MutableLiveData()
    val userProfile : LiveData<UserProfile> = _userProfile

    /**Live data for hotelRating*/
    private val _ratings = MutableLiveData<Resource<List<HotelRating>>>()
    val rating : LiveData<Resource<List<HotelRating>>> = _ratings


    fun registerUser(userData: UserDataResponseItem) {
        viewModelScope.launch {
            _newUser.postValue(ApiCallNetworkResource.Loading())
            try {
                delay(2000)
                val response = apiRepository.registerAUser(userData)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _newUser.postValue(ApiCallNetworkResource.Success("Registration Successful"))
                }else{
                    _newUser.postValue(ApiCallNetworkResource.Error(response.body()!!.message))
                }

            } catch (e: Throwable) {
                e.printStackTrace()
                when(e){
                    is IOException ->{
                        _newUser.postValue(ApiCallNetworkResource.Error(message =
                        "Network Failure, please check your internet connection"))
                    }
                    is NullPointerException ->{
                        _newUser.postValue(ApiCallNetworkResource.Error(
                            "email has already been registered"
                        ))
                    }
                    else->{
                        _newUser.postValue(ApiCallNetworkResource.Error(message =
                        "an error occur please try again later"))
                    }
                }
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
                else{
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


    fun getUserProfile (token: String){
        viewModelScope.launch {
            try {
                val response = apiRepository.getUserProfile(token)
                if (response.isSuccessful){
                    _userProfile.postValue(response.body())
                } else {
                    _userProfile.postValue(null)
                }
            } catch (e: Exception){
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

    //get all hotel
    val getAllHotel = apiRepository.getAllHotelsFomApiToDB().asLiveData()


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

    //fetch hotel review
    fun getHotelReview(id : String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                _hotelReview.postValue(Resource.Loading(null))
                val response = apiRepository.getHotelReview(id)
                if (response.isSuccessful){
                    _hotelReview.postValue(response.body()?.data?.let { Resource.Success(it.pageItems) })
                }else{
                    
                    //Handle error
//                    _hotelReview.postValue(Resource.Error(throw(response.errorBody())))
                }}catch (e : Exception){
                    e.printStackTrace()
            }
        }              }
    fun getAllWishList(token:String) = apiRepository.getAllWishList(token)

    
    fun insertWishListToDb(item: WishListDataClass){
        viewModelScope.launch { 
            try {
                apiRepository.insertWishToDataBase(item)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun deleteWishListFromDb(wishItem: WishListDataClass) {
        viewModelScope.launch {
            try {
                apiRepository.deleteWishFromDataBase(wishItem)
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }



    fun updateUserDetails (updatedUserData: PostUpdateUserData, token: String) {
        viewModelScope.launch {
            _updatedDetails.postValue(ApiCallNetworkResource.Loading())

            try {
                delay(5000)
                val response = apiRepository.updateUserDetails(updatedUserData, token)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _updatedDetails.postValue(ApiCallNetworkResource.Success("Profile Updated Succefully"))
                }else{
                    _updatedDetails.postValue(ApiCallNetworkResource.Error(response.body()!!.message))
                }

            } catch (e: Throwable) {
                e.printStackTrace()
                when(e){
                    is IOException ->{
                        _updatedDetails.postValue(ApiCallNetworkResource.Error(message =
                        "Network Failure, please check your internet connection"))
                    }
                    else->{
                        _updatedDetails.postValue(ApiCallNetworkResource.Error(message =
                        "an error occur please try again later"))
                    }
                }
            }


        }
    }

    //Response to network successfully connection or error in connection to the API
//    private suspend fun safeLoginNetworkHandler(userLoginDetails: PostLoginUserData){
//        try {
//            val response = apiRepository.userLoginDetails(userLoginDetails)
//            if(networkHandler()){
//                if(response.isSuccessful){
    fun getBookingHistory(userId : String){
        viewModelScope.launch {
            try {
                val response = apiRepository.bookingHistory(userId)
                if (response.isSuccessful) {
//                    _userLoginDetails.postValue(response.body())
                    _bookingHistory.postValue(response.body())
                } else {
                    _userLoginDetails.postValue(null)
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    fun addReviewsVM(addReviews: AddReviewsPost, token: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = apiRepository.addReviews(addReviews, token)
                if (response.isSuccessful){
                    val responseBody = response.body()
                    _addReviews.postValue(responseBody)
                }else{
                    _addReviews.postValue(null)
                }

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun addRatingsVM(hotelId: String, addRatings: AddRatingsPost, token: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = apiRepository.addRating(hotelId,addRatings,token)
                if(response.isSuccessful){
                    val responseBody = response.body()
                    _addRatings.postValue(responseBody)
                }else{
                    _addRatings.postValue(null)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }


    fun refreshToken(userId: String,refreshToken:String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = apiRepository.refreshTokenRequest(userId,refreshToken)
                if (response.isSuccessful){
                    val responseBody = response.body()
                    _refreshToken.postValue(responseBody)
                }else{
                    _refreshToken.postValue(null)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }


    fun addCustomerWishToWishList(token: String,hotelId:String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = apiRepository.addCustomerWishToWishList(token,hotelId)
                if (response.isSuccessful){
                    _addCustomerWish.postValue("wish added Successfully")
                }else{
                    _addCustomerWish.postValue("failed to add customer")
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun deleteCustomerWishFromWishList(token: String,hotelId:String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = apiRepository.deleteCustomerWishFromWishList(token,hotelId)
                if (response.isSuccessful){
                    _deleteCustomerWish.postValue("wish deleted Successfully")
                }else{
                    _deleteCustomerWish.postValue("failed to delete customer")
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun uploadImageToAPI(token: String, uri: String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = apiRepository.uploadImageToAPI(token,uri)
                if (response.isSuccessful){
                    _uploadImage.postValue("image uploaded Successful")
                }else{
                    _uploadImage.postValue("failed to upload image")
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getAllWishLIstFromAPi(token: String){

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = apiRepository.getAllWishListFromApi(token)
                if (response.isSuccessful){
                    Log.d("tokenQuery", "allWishList = ${response.body()}")

                    _getAllWishListFromAoi.postValue(response.body())
                }else{
                    _getAllWishListFromAoi.postValue(null)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getHotelRatings(id :String){

        viewModelScope.launch (Dispatchers.IO){
            try {
                _ratings.postValue(Resource.Loading(null))
                val response = apiRepository.getHotelRatings(id)
                if (response.isSuccessful){
                    _ratings.postValue(response.body()?.let { Resource.Success(it.data) })
                }else{
                    //handle error
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}




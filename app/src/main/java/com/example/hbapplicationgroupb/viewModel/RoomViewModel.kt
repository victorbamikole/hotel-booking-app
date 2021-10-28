package com.example.hbapplicationgroupb.viewModel

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
import com.example.hbapplicationgroupb.model.hotelSearchResponse.HotelSearchResponse
import com.example.hbapplicationgroupb.model.loginUserData.LoginUserDataResponse
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.model.resetPassword.ResetPasswordDataResponse
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealsAndHotel
import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.model.userData.UserDataResponse
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListDataClass
import com.example.hbapplicationgroupb.repository.ApiRepositoryInterface
import com.example.hbapplicationgroupb.util.resource.ApiCallNetworkResource
import com.example.hbapplicationgroupb.util.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
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

    private var _addReviews: MutableLiveData<AddReviewsResponse> = MutableLiveData()
    val addReviews: LiveData<AddReviewsResponse> = _addReviews
    private var _addRatings: MutableLiveData<AddRatingsResponse> = MutableLiveData()
    val addRatings: LiveData<AddRatingsResponse> = _addRatings


    init {
//        getTopHotels()
//        getAllHotels()
    }



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
    fun deleteWishListFromDb(wishItem: WishListDataClass){
        viewModelScope.launch {
            try {
                apiRepository.deleteWishFromDataBase(wishItem)
            }catch (e:Exception){
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

    //Response to network successfully connection or error in connection to the API
//    private suspend fun safeLoginNetworkHandler(userLoginDetails: PostLoginUserData){
//        try {
//            val response = apiRepository.userLoginDetails(userLoginDetails)
//            if(networkHandler()){
//                if(response.isSuccessful){
//                    _userLoginDetails.postValue(response.body())
//                }else {
//                    _userLoginDetails.postValue(null)
//                }
//            }else{
//                _userLoginDetails.postValue(null)
//            }
//        }catch (t: Throwable){
//            when(t){
//                is IOException -> _userLoginDetails.postValue(null)
//                else -> _userLoginDetails.postValue(null)
//            }
//        }
//
//    }

//    //Handle network connectivity
//
//    private fun networkHandler() : Boolean{
//        val connectivityManager = getApplication<HotelApplication>().getSystemService(
//            Context.CONNECTIVITY_SERVICE
//        ) as ConnectivityManager
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            val activeNetwork = connectivityManager.activeNetwork ?: return false
//            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
//            return when{
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//                else -> false
//            }
//        }
//        else{
//            connectivityManager.activeNetworkInfo?.run {
//                return when(type){
//                    ConnectivityManager.TYPE_WIFI -> true
//                    ConnectivityManager.TYPE_MOBILE -> true
//                    ConnectivityManager.TYPE_ETHERNET -> true
//                    else -> false
//                }
//            }
//        }
//        return false
//    }
}




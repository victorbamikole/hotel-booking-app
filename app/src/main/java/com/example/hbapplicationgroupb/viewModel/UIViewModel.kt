package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.repository.UiRepositoryInterface
import javax.inject.Inject


@HiltViewModel
class UIViewModel @Inject constructor(
    private val uiRepository: UiRepositoryInterface
) : ViewModel(){

//    //User Added
//    val newUser: MutableLiveData<Response<UserDataResponseItem>> = MutableLiveData()

//    fun registerUser (userData : UserDataResponseItem) {
//        viewModelScope.launch {
//            val response = uiRepository.registerAUser(userData)
//            newUser.value = response
//        }
//    }


    //List of all hotels
   lateinit var allHotels :LiveData<List<HotelData>>

    fun getAllHotels(){
        viewModelScope.launch {
            allHotels = uiRepository.getAllHotels()
        }
    }
}
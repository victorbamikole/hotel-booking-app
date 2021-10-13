package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.repository.ApiToRoomRepositoryImpl
import com.example.hbapplicationgroupb.repository.RoomToUiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(
    val roomToUiRepository: ApiToRoomRepositoryImpl
) : ViewModel(){
    val newUser: MutableLiveData<Response<UserDataResponseItem>> = MutableLiveData()

    fun registerUser (userData : UserDataResponseItem) {
        viewModelScope.launch {
            val response = roomToUiRepository.registerAUser(userData)
            newUser.value = response
        }
    }

}
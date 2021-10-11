package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.Data
import com.example.hbapplicationgroupb.model.allHotels.GetAllHotel
import com.example.hbapplicationgroupb.repository.ApiRepositoryImpl
import com.example.hbapplicationgroupb.repository.ApiRepositoryInterface
import com.example.hbapplicationgroupb.repository.UiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(
    private val uiRepository: ApiRepositoryInterface
) : ViewModel(){

    private val _allHotels = MutableLiveData<List<Data>>()
    val allHotels :LiveData<List<Data>> get() =  _allHotels


    fun getAllHotels(){
        viewModelScope.launch {
            val response= uiRepository.getAllHotels()
            if (response.isSuccessful){
                _allHotels.postValue(response.body()?.data)
            }
        }
    }
}
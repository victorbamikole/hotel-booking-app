package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionData
import com.example.hbapplicationgroupb.repository.UiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(
    private val uiRepository: UiRepositoryInterface
) : ViewModel(){

    //List of all hotels
    lateinit var allHotels :LiveData<List<HotelData>>


    fun getAllHotels(){
        viewModelScope.launch {
            allHotels = uiRepository.getAllHotels()

        }
    }


}
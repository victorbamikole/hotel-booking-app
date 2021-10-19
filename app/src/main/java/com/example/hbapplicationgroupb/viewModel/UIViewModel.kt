package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.repository.UiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(
    private val uiRepository: UiRepositoryInterface
) : ViewModel(){

//fun saveTopHotel(topHotelData: TopHotelData) = viewModelScope.launch {
//    uiRepository.insertHotelToDatabase(topHotelData)
//}
//
//    fun getAllTopHotel() = uiRepository.getAllTopHotels()
//
}
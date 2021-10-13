package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupb.model.tophoteldata.HotelTopDealItems
import com.example.hbapplicationgroupb.repository.RoomToUiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(
    private val roomToUiRepository: RoomToUiRepositoryInterface
) : ViewModel(){

}
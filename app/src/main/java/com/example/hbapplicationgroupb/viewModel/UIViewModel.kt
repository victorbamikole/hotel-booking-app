package com.example.hbapplicationgroupb.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData
import com.example.hbapplicationgroupb.repository.UiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UIViewModel @Inject constructor (
    private val uiRepository: UiRepositoryInterface
) : ViewModel(){
private var _listOfHotels:MutableLiveData<List<HotelData>> = MutableLiveData()
    val listOfHotels:LiveData<List<HotelData>> = _listOfHotels
//   fun addAllHotelsToDb(hotelList: List<TopDealAndHotelData>){
//        viewModelScope.launch(Dispatchers.IO){
//            uiRepository.addAllHotelsToDb(hotelList)
//        }
//    }

    fun insertAllHotelsToDb(hotelList: ArrayList<TopDealAndHotelData>){
        viewModelScope.launch(Dispatchers.IO){
            uiRepository.insertAllHotelsToDb(hotelList)
        }
    }
//fun saveTopHotel(topHotelData: TopHotelData) = viewModelScope.launch {
//    uiRepository.insertHotelToDatabase(topHotelData)
//}
//
//    fun getAllTopHotel() = uiRepository.getAllTopHotels()
//

}
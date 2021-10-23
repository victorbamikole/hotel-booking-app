package com.example.hbapplicationgroupb.viewModel


import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupb.repository.UiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UIViewModel @Inject constructor (
    private val uiRepository: UiRepositoryInterface
) : ViewModel(){
//private var _listOfHotels:MutableLiveData<List<HotelData>> = MutableLiveData()
//    val listOfHotels:LiveData<List<HotelData>> = _listOfHotels
//   fun addAllHotelsToDb(hotelList: List<TopDealAndHotelData>){
//        viewModelScope.launch(Dispatchers.IO){
//            uiRepository.addAllHotelsToDb(hotelList)
//        }
//    }

//    fun insertAllHotelsToDb(hotelList: ArrayList<TopDealAndHotelData>){
//        viewModelScope.launch(Dispatchers.IO){
//            uiRepository.insertAllHotelsToDb(hotelList)
//        }
//    }
//fun saveTopHotel(topHotelData: TopHotelData) = viewModelScope.launch {
//    uiRepository.insertHotelToDatabase(topHotelData)
//}
//
//    fun getAllTopHotel() = uiRepository.getAllTopHotels()
//

}
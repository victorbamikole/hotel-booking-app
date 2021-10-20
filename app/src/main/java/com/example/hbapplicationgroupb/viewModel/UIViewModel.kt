package com.example.hbapplicationgroupb.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.model.allHotels.HotelData
import com.example.hbapplicationgroupb.repository.UiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UIViewModel @Inject constructor(
    private val UiRepository : UiRepositoryInterface
) : ViewModel(){
private var _listOfHotels:MutableLiveData<List<HotelData>> = MutableLiveData()
    val listOfHotels:LiveData<List<HotelData>> = _listOfHotels
   fun addAllHotelsToDb(hotelList: List<HotelData>){
        viewModelScope.launch(Dispatchers.IO){
            UiRepository.addAllHotelsToDb(hotelList)
//            try {
//                val response = UiRepository.addAllHotelsToDb(hotelList as ArrayList<HotelData>)
//                _listOfHotels.postValue(response)
//            }catch (e:Exception){
//                e.printStackTrace()
//            }

        }
    }
}
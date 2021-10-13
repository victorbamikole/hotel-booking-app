package com.example.hbapplicationgroupb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupb.dataBase.dao.GetAllHotelsItemDao
import com.example.hbapplicationgroupb.di.HotelModule
import com.example.hbapplicationgroupb.model.api.NetworkCall
import com.example.hbapplicationgroupb.model.tophoteldata.HotelTopDealItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class RoomToUIRepositoryImpl @Inject constructor(
//    db : HotelDatabase
) :  RoomToUiRepositoryInterface, ViewModel(){

}
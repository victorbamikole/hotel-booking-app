package com.example.hbapplicationgroupb.viewModel

import com.example.hbapplicationgroupb.repository.ApiToRoomRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
   val repository : ApiToRoomRepositoryInterface
){


}
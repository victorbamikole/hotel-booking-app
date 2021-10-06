package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupb.repository.ApiToRoomRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
   val apiToRoomRepository : ApiToRoomRepositoryInterface
) : ViewModel(){


}
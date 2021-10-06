package com.example.hbapplicationgroupb.viewModel

import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupb.repository.RoomToUiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(
    roomToUiRepository: RoomToUiRepositoryInterface
) : ViewModel(){
}
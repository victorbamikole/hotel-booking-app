package com.example.hbapplicationgroupb.viewModel

import com.example.hbapplicationgroupb.repository.RoomToUiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(repositoryInterface: RoomToUiRepositoryInterface){
}
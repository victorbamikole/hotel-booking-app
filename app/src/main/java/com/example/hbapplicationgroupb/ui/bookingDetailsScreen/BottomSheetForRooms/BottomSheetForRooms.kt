package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetForRooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hbapplicationgroupb.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetForRooms : BottomSheetDialogFragment() {

    fun bottomSheet(){
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.room_number_bottom_sheet, container, false)
    }
}
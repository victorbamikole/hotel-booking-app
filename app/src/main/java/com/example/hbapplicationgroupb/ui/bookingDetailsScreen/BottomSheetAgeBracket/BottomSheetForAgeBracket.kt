package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetAgeBracket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hbapplicationgroupb.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetForAgeBracket: BottomSheetDialogFragment() {

    fun bottomSheet(){
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.age_bracket_bottom_sheet_layout, container, false)
    }
}
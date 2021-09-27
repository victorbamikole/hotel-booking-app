package com.example.hbapplicationgroupb.ui.hoteldescription

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentHotelDescriptionBinding

class HotelDescriptionFragment : Fragment(R.layout.fragment_hotel_description) {
    private lateinit var binding:FragmentHotelDescriptionBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHotelDescriptionBinding.bind(view)


    }
}
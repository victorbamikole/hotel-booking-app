package com.example.hbapplicationgroupb.ui.bookinghistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentBookingHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingHistoryFragment : Fragment(R.layout.fragment_booking_history) {

    //var of type of binding class created for xml file
    private lateinit var binding: FragmentBookingHistoryBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set binding to bind views when views have created
        binding = FragmentBookingHistoryBinding.bind(view)
        binding.bookingHistoryBackArrow.setOnClickListener {
            findNavController().navigate(R.id.action_bookingHistoryFragment_to_profileFragment2)
        }

    }
}
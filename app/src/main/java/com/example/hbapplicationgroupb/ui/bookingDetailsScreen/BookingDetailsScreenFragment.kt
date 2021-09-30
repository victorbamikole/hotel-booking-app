package com.example.hbapplicationgroupb.ui.bookingDetailsScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentBookingDetailsScreenBinding


class BookingDetailsScreenFragment : Fragment(R.layout.fragment_booking_details_screen) {
    private lateinit var binding: FragmentBookingDetailsScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookingDetailsScreenBinding.bind(view)
        binding.fragmentBookingBookNowButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_bookingDetailsScreenFragment_to_bookingConfirmationFragment)
        }
    }
}
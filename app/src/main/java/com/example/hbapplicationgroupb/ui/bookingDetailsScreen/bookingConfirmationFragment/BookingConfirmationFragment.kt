package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.bookingConfirmationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentBookingConfirmationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingConfirmationFragment : Fragment(R.layout.fragment_booking_confirmation) {
    private lateinit var binding: FragmentBookingConfirmationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookingConfirmationBinding.bind(view)
        binding.fragmentConfirmationScreenBackButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_bookingConfirmationFragment_to_exploreFragment2)
        }
    }
}
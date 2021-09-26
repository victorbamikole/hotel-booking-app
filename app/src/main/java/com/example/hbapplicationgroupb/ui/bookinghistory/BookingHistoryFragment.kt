package com.example.hbapplicationgroupb.ui.bookinghistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentAddReviewPageBinding

class BookingHistoryFragment : Fragment(R.layout.fragment_booking_history) {

    //var of type of binding class created for xml file
    private lateinit var binding: FragmentAddReviewPageBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set binding to bind views when views have created
        binding = FragmentAddReviewPageBinding.bind(view)

    }
}
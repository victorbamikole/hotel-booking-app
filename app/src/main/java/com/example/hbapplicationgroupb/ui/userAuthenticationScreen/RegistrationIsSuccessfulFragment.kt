package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentRegistrationIsSuccessfulBinding
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.bookingDetailsScreenFragment.BookingDetailsScreenFragmentArgs


class RegistrationIsSuccessfulFragment : Fragment(R.layout.fragment_registration_is_successful) {
    private lateinit var binding:FragmentRegistrationIsSuccessfulBinding
    private val safeArgs : RegistrationIsSuccessfulFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationIsSuccessfulBinding.bind(view)
        val userData = safeArgs.userData

        binding.emailFromSafeArgs.text = userData.email
    }
}
package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentRegistrationIsSuccessfulBinding


class RegistrationIsSuccessfulFragment : Fragment(R.layout.fragment_registration_is_successful) {
    private lateinit var binding:FragmentRegistrationIsSuccessfulBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationIsSuccessfulBinding.bind(view)
    }
}
package com.example.hbapplicationgroupb.ui.privacy_policy_screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentPrivacyPolicyBinding


class PrivacyPolicyFragment : Fragment(R.layout.fragment_privacy_policy) {
    private lateinit var binding : FragmentPrivacyPolicyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrivacyPolicyBinding.bind(view)

        binding.fragmentPrivacyPolicyBackArrowViev.setOnClickListener {
            findNavController()
                .navigate(R.id.action_privacyPolicyFragment_to_profileFragment2)
        }

    }





}
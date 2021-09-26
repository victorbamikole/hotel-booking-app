package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    var binding : FragmentProfileBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
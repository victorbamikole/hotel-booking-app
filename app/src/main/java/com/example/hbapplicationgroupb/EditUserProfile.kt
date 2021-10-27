package com.example.hbapplicationgroupb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hbapplicationgroupb.databinding.FragmentEditUserProfileBinding

class EditUserProfile : Fragment(R.layout.fragment_edit_user_profile) {

    var binding: FragmentEditUserProfileBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditUserProfileBinding.bind(view)

        binding?.EditProfileFragmentUpdateButton?.setOnClickListener {
            var updatedFirstName = binding?.EditProfileFragmentEditTextViewFirstName?.text?.trim().toString()
            var updatedLastName = binding?.EditProfileFragmentEditTexLastName?.text?.trim().toString()
            var updatedPhoneNumber = binding?.EditProfileFragmentEditTextPhoneNumber?.text?.trim().toString()
            var updatedAddress = binding?.EditProfileFragmentEditTextAddress?.text?.trim().toString()
            var updatedState = binding?.EditProfileFragmentEditTextState?.text?.trim().toString()

        }

    }

}
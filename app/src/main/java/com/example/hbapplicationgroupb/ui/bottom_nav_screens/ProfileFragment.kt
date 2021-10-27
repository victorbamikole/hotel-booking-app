package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentProfileBinding
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetForRooms.BottomSheetForRooms


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    var binding : FragmentProfileBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        binding?.fragmentProfileLogOutBtn?.setOnClickListener {
            //Clear user token from shared preferences
            activity?.let { it1 -> UserPreferences(it1).clearUserSession() }
            showLogOutAlert()
        }

        /** Method to pop bottom Sheet for Room type Selection */
        binding?.fragmentProfileEditProfileTv?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_editUserProfile)
        }

        binding?.fragmentProfileHistoryTv?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_bookingHistoryFragment)
        }

        binding?.fragmentProfileHistoryIcon?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_bookingHistoryFragment)
        }

        binding?.fragmentProfileHelpTv?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_helpAndSupportFragment)
        }

        binding?.fragmentProfileHelpIcon?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_helpAndSupportFragment)
        }

        binding?.fragmentPrivacyPolicyTextView?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_privacyPolicyFragment)
        }
        binding?.fragmentProfilePrivacyIcon?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_privacyPolicyFragment)
        }
    }

    private fun showLogOutAlert(){
        val dialogView = layoutInflater.inflate(R.layout.custom_profile_dialog, null)
        val customDialog = activity?.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
                .show()
        }

        val btnProfileLogOutDialog = dialogView.findViewById<Button>(R.id.fragment_profile_log_out_btn)
        btnProfileLogOutDialog.setOnClickListener {
            customDialog?.dismiss()

            //Navigate to the login screen
            findNavController().navigate(R.id.action_profileFragment2_to_loginFragment)

        }

        val btnProfileCancelDialog = dialogView.findViewById<Button>(R.id.fragment_profile_cancel_btn)
        btnProfileCancelDialog.setOnClickListener {
            customDialog?.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}
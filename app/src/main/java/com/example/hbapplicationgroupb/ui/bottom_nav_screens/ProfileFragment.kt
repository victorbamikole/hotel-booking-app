package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    var binding : FragmentProfileBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        binding?.fragmentProfileLogOutBtn?.setOnClickListener {
            showLogOutAlert()
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
            Toast.makeText(activity,"Log out Button Clicked", Toast.LENGTH_LONG).show()
        }

        val btnProfileCancelDialog = dialogView.findViewById<Button>(R.id.fragment_profile_cancel_btn)
        btnProfileCancelDialog.setOnClickListener {
            Toast.makeText(activity,"Cancel Button Clicked", Toast.LENGTH_LONG).show()
            customDialog?.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
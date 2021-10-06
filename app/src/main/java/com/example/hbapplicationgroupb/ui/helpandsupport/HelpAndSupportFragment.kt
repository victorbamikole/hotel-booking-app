package com.example.hbapplicationgroupb.ui.helpandsupport


import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentHelpAndSupportBinding


class HelpAndSupportFragment : Fragment(R.layout.fragment_help_and_support) {

    //var of type of binding class created for xml file
    private lateinit var binding: FragmentHelpAndSupportBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set binding to bind views when views have created
        binding = FragmentHelpAndSupportBinding.bind(view)

        binding.helpAndSupportFragmentBackArrow.setOnClickListener {
            findNavController()
                .navigate(R.id.action_helpAndSupportFragment_to_profileFragment2)
        }
    }
}
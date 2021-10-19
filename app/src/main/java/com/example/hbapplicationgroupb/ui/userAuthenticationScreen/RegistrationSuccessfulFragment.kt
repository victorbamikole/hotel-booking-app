package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentRegistrationSuccessfulBinding


class RegistrationSuccessfulFragment : Fragment(R.layout.fragment_registration_successful) {
    private lateinit var binding : FragmentRegistrationSuccessfulBinding
//    private val args by navArgs<RegistrationSuccessfulFragmentArgs>()

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationSuccessfulBinding.bind(view)

//     binding.textViewUserNameRegistrationSuccessful.text = args.userDetails.userName
//     binding.textViewUserEmailRegistrationSuccessful.text = args.userDetails.email


        /** the correct navigation destination yet to be implemented*/
        binding.fragmentConfirmationScreenBackButtonToLoging.setOnClickListener {

//            findNavController().navigate(R.id.action_successfullyRegisteredFragment_to_loginFragment)
        }
    }
}
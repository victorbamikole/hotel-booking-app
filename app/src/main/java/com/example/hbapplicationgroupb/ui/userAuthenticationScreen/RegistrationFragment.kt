package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private lateinit var _binding : FragmentRegistrationBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set click listener on login link
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {
            Toast.makeText(activity, "Registration successful", Toast.LENGTH_SHORT).show()
        }

        binding.tvPrivacyPolicy.setOnClickListener {
            findNavController()
                .navigate(R.id.action_registrationFragment_to_privacyPolicyFragment)
        }
    }

}
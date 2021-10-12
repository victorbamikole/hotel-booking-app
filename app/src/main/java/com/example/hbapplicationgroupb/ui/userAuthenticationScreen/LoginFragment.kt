package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding:FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        binding.tvUserLoginPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
        binding.btnLogin.setOnClickListener {
           findNavController().navigate(R.id.action_loginFragment_to_exploreFragment2)
        }

        //Toggle enable sign up button
        binding.tvUserLoginEmail.addTextChangedListener(loginButtonHandler)
        binding.tvUserPassword.addTextChangedListener(loginButtonHandler)

    }


    //Login button handler
    //If the two text fields are empty, the login button will be disabled
    private val loginButtonHandler : TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val userLoginEmail : String = binding.tvUserLoginEmail.text.toString().trim()
            val userLoginPassword : String = binding.tvUserPassword.text.toString().trim()
            binding.btnLogin.isEnabled = userLoginEmail.isNotEmpty() && userLoginPassword.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}

    }
}
package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentForgotPasswordBinding
import com.example.hbapplicationgroupb.validation.ForgetPasswordValidationFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {
    private lateinit var binding:FragmentForgotPasswordBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPasswordBinding.bind(view)
        binding.tvLogin.setOnClickListener {
           findNavController()
                .navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }
        binding.btnForgotPassword.setOnClickListener {
            if (ForgetPasswordValidationFunctions.checkIfFieldNotEmpty(binding.tvForgotPasswordEmail.text.toString())){
                if (ForgetPasswordValidationFunctions.checkIfEmailIsValid(binding.tvForgotPasswordEmail.text.toString())){

                }else{
                    binding.tvForgotPasswordEmail.error = "enter a valid email"
                }
            }
        }
    }
}
package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentForgotPasswordBinding
import com.example.hbapplicationgroupb.validation.ForgetPasswordValidationFunctions
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {
    private lateinit var binding:FragmentForgotPasswordBinding
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPasswordBinding.bind(view)
        binding.tvLogin.setOnClickListener {
           findNavController()
                .navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }
        roomViewModel.forgotPasswordData.observe(viewLifecycleOwner,{
            Toast.makeText(context,"$it",Toast.LENGTH_LONG).show()
        })
        binding.btnForgotPassword.setOnClickListener {
            if (ForgetPasswordValidationFunctions.checkIfFieldNotEmpty(binding.tvForgotPasswordEmail.text.toString())){
                if (ForgetPasswordValidationFunctions.checkIfEmailIsValid(binding.tvForgotPasswordEmail.text.toString())){
                    roomViewModel.sendForgetPasswordEmailToApi(
                        binding.tvForgotPasswordEmail.text.toString()
                    )
                }else{
                    binding.tvForgotPasswordEmail.error = "enter a valid email"
                }
            }else{
                binding.tvForgotPasswordEmail.error = "email field cannot be empty"
            }
        }
    }
}
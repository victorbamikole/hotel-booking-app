package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentResetPasswordBinding
import com.example.hbapplicationgroupb.model.resetPassword.PostResetPasswordData
import com.example.hbapplicationgroupb.validation.ResetPasswordValidationFunctions
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : Fragment(R.layout.fragment_reset_password) {

    private lateinit var binding:FragmentResetPasswordBinding
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResetPasswordBinding.bind(view)

        //get token and email from deep link
        val userToken = arguments?.getString("token")
        val userEmail = arguments?.getString("email")

        roomViewModel.resetPasswordData.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(),"$it",Toast.LENGTH_LONG).show()
        })

        binding.btnReset.setOnClickListener {

            if(ResetPasswordValidationFunctions.checkIfFieldNotEmpty(binding.tvResetPassword.text.toString())){
                if (ResetPasswordValidationFunctions.checkIfPassWordIsValid(binding.tvResetPassword.text.toString())==0){
                    if (ResetPasswordValidationFunctions.checkIfPasswordMatches(binding.tvResetPassword.text.toString(),binding.tvConfirmPassword.text.toString())){
                        roomViewModel.sendNewPasswordToAPI(
                            PostResetPasswordData(
                                token = userToken.toString(),
                                email = userEmail.toString(),
                                newPassword = binding.tvResetPassword.text.toString(),
                                confirmPassword = binding.tvConfirmPassword.text.toString()
                            ))
                        Toast.makeText(requireContext(), "Password changed successfully", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
                        Log.d("PASSWORD RESET", "PASSWORD RESET")
                    }else{
                        binding.tvResetPassword.error = "password does not match"
                        binding.tvConfirmPassword.error = "password does not match"
                    }
                }else {
                    when(ResetPasswordValidationFunctions.checkIfPassWordIsValid(binding.tvResetPassword.text.toString())){
                        1 -> {
                            binding.tvResetPassword.error = "password must be more than 8"
                            binding.tvResetPassword.isFocusable
                        }
                        2 -> {
                            binding.tvResetPassword.error = "password must contain number"
                            binding.tvResetPassword.isFocusable
                        }
                        3 -> {
                            binding.tvResetPassword.error = "password must contain special character"
                            binding.tvResetPassword.isFocusable
                        }
                        4 -> {
                            binding.tvResetPassword.error = "password must contain at least 1 lowercase alphabet"
                            binding.tvResetPassword.isFocusable
                        }
                        5 -> {
                            binding.tvResetPassword.error = "password must contain at least 1 uppercase alphabet"
                            binding.tvResetPassword.isFocusable
                        }
                    }
                    binding.tvResetPassword.error = "password must be more than six characters"
                }

            }else{
                binding.tvResetPassword.error = "this field cannot be empty"
            }
        }


        binding.tvResetPasswordLogin.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
        }

    }

}

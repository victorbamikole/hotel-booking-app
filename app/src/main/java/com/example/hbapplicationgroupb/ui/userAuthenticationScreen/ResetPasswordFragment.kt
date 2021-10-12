package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
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
            Toast.makeText(context,"$it",Toast.LENGTH_LONG).show()
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
                   }else{
                       binding.tvResetPassword.error = "password does not match"
                       binding.tvConfirmPassword.error = "password does not match"
                   }
               }else if (ResetPasswordValidationFunctions.checkIfPassWordIsValid(binding.tvResetPassword.text.toString())==1){
                   binding.tvResetPassword.error = "password must be more than six characters"
               }else if(ResetPasswordValidationFunctions.checkIfPassWordIsValid(binding.tvResetPassword.text.toString())==2){
                   binding.tvResetPassword.error = "password must contain number, letter and special characters"
               }

            }else{
                binding.tvResetPassword.error = "this field cannot be empty"
            }

            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
        }
        binding.tvResetPasswordLogin.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
        }

    }

}
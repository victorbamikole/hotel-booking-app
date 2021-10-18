package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentLoginBinding
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.validation.LoginValidation
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.tvUserLoginPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        roomViewModel.userLoginDetails.observe(viewLifecycleOwner, Observer {

            if (it.succeeded) {
                lifecycleScope.launch {
                    findNavController().navigate(R.id.action_loginFragment_to_exploreFragment2)
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(requireActivity(), "Invalid login details", Toast.LENGTH_LONG).show()
            }
        })

        //Toggle enable sign up button
        binding.tvUserLoginEmail.addTextChangedListener(loginButtonHandler)
        binding.tvUserPassword.addTextChangedListener(loginButtonHandler)

        binding.btnLogin.setOnClickListener {
<<<<<<< HEAD
            val usersEmail = binding.tvUserLoginEmail.text.toString().trim()
            val usersPassword = binding.tvUserPassword.text.toString().trim()

            if(LoginValidation.validateEmailPattern(usersEmail)) {
                if (LoginValidation.validatePasswordPattern(usersPassword)) {
                    roomViewModel.sendUserLoginDetailsToApi(PostLoginUserData(usersEmail, usersPassword))
                    roomViewModel.userLoginDetails.observe(
                        viewLifecycleOwner, {
                            if(it.succeeded) {
                                findNavController().navigate(R.id.action_loginFragment_to_exploreFragment2)
                            }
                        }
                    )
                }
                else{
                    binding.regPasswordInput.error = "Password does not match with any email address"
                }
=======
            login()
        }
    }

    private fun login() {
        val usersEmail = binding.tvUserLoginEmail.text.toString().trim()
        val usersPassword = binding.tvUserPassword.text.toString().trim()
        if(LoginValidation.validateEmailPattern(usersEmail)){
            if (LoginValidation.validatePasswordPattern(usersPassword)){
                roomViewModel.sendUserLoginDetailsToApi(PostLoginUserData(usersEmail, usersPassword))
>>>>>>> 114f2fc33fd30a616b04a2da419305ae2a106126
            }
            else{
                binding.regPasswordInput.error = "Password does not match with any email address"
            }
        }
        else{
            binding.regEmailInput.error = "Invalid email address"
        }

    }


    //Login button handler
//    If the two text fields are empty, the login button will be disabled
    private val loginButtonHandler: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val userLoginEmail: String = binding.tvUserLoginEmail.text.toString().trim()
            val userLoginPassword: String = binding.tvUserPassword.text.toString().trim()
            binding.btnLogin.isEnabled =
                userLoginEmail.isNotEmpty() && userLoginPassword.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}

    }
}

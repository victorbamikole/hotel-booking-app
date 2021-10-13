package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentRegistrationBinding
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private lateinit var _binding : FragmentRegistrationBinding
    private val binding get() = _binding
    private val viewModel: RoomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




//        val radioButton = binding.btnRadio

//        var userData: UserDataResponseItem = UserDataResponseItem(
//            firstName = firstName,
//            lastName = lastName,
//            email  = email,
//            userName = "TestUser1",
//            password = password,
//            phoneNumber = phoneNumber,
//            gender = gender,
//            age = 20 )




        //Set click listener on login link
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }


        binding.btnRegister.setOnClickListener {
            val firstName = binding.editTextViewRegUsername.text.toString()
            val lastName = binding.editTextUserLastName.text.toString()
            val phoneNumber = binding.editTextRegUserPhoneNumber.text.toString()
            val gender = binding.editTextRegUserGender.text.toString()
            val email = binding.editTextRegUserEmail.text.toString()
            val password = binding.editTextRegPassword.editText!!.text.toString()

            val userDataTest = UserDataResponseItem(firstName,lastName,email,
                "TestUser4",password,phoneNumber,gender,20)
//            if (RegistrationValidation.validateUserName(firstName)){
//                binding.tvRegUserName.error = "User Name field must not be empty"
//                binding.tvRegUserName.isFocusable
//                return@setOnClickListener
//            }
//
//
//            if (RegistrationValidation.validateEmail(email)){
//                binding.tvRegUserEmail.error = "Email field must not be empty"
//                binding.tvRegUserEmail.isFocusable
//                return@setOnClickListener
//            }
//
//
//            if (RegistrationValidation.validatePassword(password)){
//                binding.regPasswordInput.error = "Enter a valid password"
//                 binding.regPasswordInput.isFocusable
//                return@setOnClickListener
//            }


//            if(RegistrationValidation.validateRadioButtonIsChecked(radioButton)){
//                radioButton.error = "Agree to the terms and condition"
//                radioButton.requestFocus()
//                return@setOnClickListener
//            }

            viewModel.registerUser(userDataTest)
            viewModel.newUser.observe(requireActivity(), { response ->
                if (response.isSuccessful){
                    Toast.makeText(activity, "Registration successful", Toast.LENGTH_SHORT).show()

                    /** the correct navigation destination yet to be implemented*/
//                    val action = RegistrationFragmentDirections
//                        .actionRegistrationFragmentToSuccessfullyRegisteredFragment(userData)
//                    findNavController().navigate(action)
                }
            })
        }


        binding.tvPrivacyPolicy.setOnClickListener {
            findNavController()
                .navigate(R.id.action_registrationFragment_to_privacyPolicyFragment)
        }
    }
}

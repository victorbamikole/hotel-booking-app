package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aminography.primedatepicker.utils.gone
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentRegistrationBinding
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.validation.RegistrationValidation
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val progressBar = binding.fragmentRegistrationProgressBar
//        progressBar.visibility = View.GONE

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
            val radioButton = binding.btnRadio
            val userName = CreateUserName.createUserName(firstName,lastName)

            val userDataTest = UserDataResponseItem(firstName,lastName,email,
                userName,password,phoneNumber,gender,20)

            /**Field Validation*/
            if (!RegistrationValidation.validateUserFirstName(firstName)){
                binding.editTextViewRegUsername.error = "Enter a proper name"
                binding.editTextViewRegUsername.isFocusable
                return@setOnClickListener
            }


            if (!RegistrationValidation.validateUserLastName(lastName)){
                binding.editTextUserLastName.error = "Enter a proper name"
                binding.editTextUserLastName.isFocusable
                return@setOnClickListener
            }


            if (!RegistrationValidation.validateUserPhoneNumber(phoneNumber)){
                binding.editTextRegUserPhoneNumber.error = "Enter a valid phone number"
                binding.editTextRegUserPhoneNumber.isFocusable
                return@setOnClickListener
            }


            if (!RegistrationValidation.validateUserGender(gender)){
                binding.editTextRegUserGender.error = "Field must not be empty"
                binding.editTextRegUserGender.isFocusable
                return@setOnClickListener
            }


            if (!RegistrationValidation.validateEmail(email)){
                binding.editTextRegUserEmail.error = "Email field must not be empty"
                binding.editTextRegUserEmail.isFocusable
                return@setOnClickListener

            }


            if (!RegistrationValidation.validatePassword(password)){
                binding.editTextRegPassword.error = "Enter a valid password"
                 binding.editTextRegPassword.isFocusable
                return@setOnClickListener
            }


            if(!RegistrationValidation.validateRadioButtonIsChecked(radioButton)){
                radioButton.error = "Agree to the terms and condition"
                radioButton.requestFocus()
                return@setOnClickListener

            }
            viewModel.registerUser(userDataTest)
            viewModel.newUser.observe(requireActivity(), {
                if (it.isSuccessful){
                    /** the correct navigation destination yet to be implemented*/
                    Toast.makeText(activity, "Registration successful", Toast.LENGTH_SHORT).show()
                }

            })
        }

        binding.tvPrivacyPolicy.setOnClickListener {
            findNavController()
                .navigate(R.id.action_registrationFragment_to_privacyPolicyFragment)
        }
    }
}

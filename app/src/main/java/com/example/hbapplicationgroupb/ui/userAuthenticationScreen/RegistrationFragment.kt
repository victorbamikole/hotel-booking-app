package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.aminography.primedatepicker.utils.gone
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentRegistrationBinding
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.validation.RegistrationValidation
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private lateinit var _binding: FragmentRegistrationBinding
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
        binding.editTextRegUserAge.setText("0")

        binding.btnRegister.setOnClickListener {

            val firstName = binding.editTextViewRegUsername.text.toString()
            val lastName = binding.editTextUserLastName.text.toString()
            val phoneNumber = binding.editTextRegUserPhoneNumber.text.toString()
            val gender = binding.editTextRegUserGender.text.toString()
            val email = binding.editTextRegUserEmail.text.toString()
            val password = binding.editTextRegUserPassword.text.toString()
            val radioButton = binding.btnRadio
            val age = binding.editTextRegUserAge.text.toString().toInt()
            val userName = binding.editTextUserUserName.text.toString()

            val userDataTest = UserDataResponseItem(
                firstName, lastName, email,
                userName, password, phoneNumber, gender, age
            )

            /**Field Validation*/
            if (!RegistrationValidation.validateUserFirstName(firstName)) {
                binding.editTextViewRegUsername.error = "Enter a First name"
                binding.editTextViewRegUsername.isFocusable
                return@setOnClickListener
            }


            if (!RegistrationValidation.validateUserLastName(lastName)) {
                binding.editTextUserLastName.error = "Enter a Last name"
                binding.editTextUserLastName.isFocusable
                return@setOnClickListener
            }


            if (!RegistrationValidation.validateUserPhoneNumber(phoneNumber)) {
                binding.editTextRegUserPhoneNumber.error = "Enter a valid phone number"
                binding.editTextRegUserPhoneNumber.isFocusable
                return@setOnClickListener
            }


            if (!RegistrationValidation.validateUserGender(gender)) {
                binding.editTextRegUserGender.error = "Field must not be empty"
                binding.editTextRegUserGender.isFocusable
                return@setOnClickListener
            }


            if (!RegistrationValidation.validateEmail(email)) {
                binding.editTextRegUserEmail.error = "Email field must not be empty"
                binding.editTextRegUserEmail.isFocusable
                return@setOnClickListener

            }


            if (!RegistrationValidation.validatePassword(password)) {
                binding.editTextRegUserPassword.error = "Enter a valid password"
                binding.editTextRegUserPassword.isFocusable
                return@setOnClickListener
            }


            if (!RegistrationValidation.validateRadioButtonIsChecked(radioButton)) {
                radioButton.error = "Agree to the terms and condition"
                radioButton.requestFocus()
                return@setOnClickListener

            }
            binding.viewCover.visibility = View.VISIBLE
            binding.registerProgressBar.visibility = View.VISIBLE

            viewModel.registerUser(userDataTest)
            registrationResponseObserver()

            val coverScreenTimeout = 5000
            Handler(Looper.getMainLooper()).postDelayed({
                binding.viewCover.visibility = View.GONE
                binding.registerProgressBar.visibility = View.GONE

            }, coverScreenTimeout.toLong())

        }

            binding.tvPrivacyPolicy.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_registrationFragment_to_privacyPolicyFragment)
            }

        }

    private fun registrationResponseObserver() {

        viewModel.newUser.observe(viewLifecycleOwner, {
            if (it != null) {
                if (it.succeeded){
                    binding.viewCover.visibility = View.GONE
                    binding.registerProgressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registrationFragment_to_registrationIsSuccessfulFragment)
                }else{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                }else{
                Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_LONG).show()
            }
        })

    }
}

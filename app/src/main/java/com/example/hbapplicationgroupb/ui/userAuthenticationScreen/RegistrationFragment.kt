package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.aminography.primedatepicker.utils.gone
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentRegistrationBinding
import com.example.hbapplicationgroupb.di.application.HotelApplication.Companion.application
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.util.resource.ApiCallNetworkResource
import com.example.hbapplicationgroupb.util.resource.ConnectivityLiveData
import com.example.hbapplicationgroupb.util.resource.Resource
import com.example.hbapplicationgroupb.util.resource.observeNetworkConnection
import com.example.hbapplicationgroupb.validation.RegistrationValidation
import com.example.hbapplicationgroupb.validation.ResetPasswordValidationFunctions
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private lateinit var _binding: FragmentRegistrationBinding
    private val binding get() = _binding
    private val viewModel: RoomViewModel by viewModels()
    private  lateinit var userDataTest:UserDataResponseItem
    private lateinit var connectivityLiveData:ConnectivityLiveData
    private lateinit var selectedGender:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(application)
    }

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
        selectedGender = "Male"
        //set content of gender field
        val listOfGender = resources.getStringArray(R.array.Gender)
        val genderAdapter = ArrayAdapter(requireContext(),R.layout.array_adapter_for_gender_layout,
            listOfGender)
        binding.editTextRegUserGender.setAdapter(genderAdapter)

        binding.editTextRegUserGender.onItemClickListener = object :AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedGender = listOfGender[p2].toString()
            }

        }

        //observe network state
        observeNetworkConnection(connectivityLiveData,viewLifecycleOwner,
            { doThisWhenNetworkIsAvailable() }, { doThisWhenNetworkIsLost() })

        //Set click listener on login link
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        registrationResponseObserver()
        binding.btnRegister.setOnClickListener {
            val firstName = binding.editTextViewRegUsername.text.toString().trim()
            val lastName = binding.editTextUserLastName.text.toString().trim()
            val phoneNumber = binding.editTextRegUserPhoneNumber.text.toString().trim()
            val gender = selectedGender
            val email = binding.editTextRegUserEmail.text.toString().trim()
            val password = binding.editTextRegUserPassword.text.toString().trim()
            val radioButton = binding.btnRadio
            val age = 20
            val userName = createUserName(firstName,lastName)

        userDataTest = UserDataResponseItem(
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


            if (ResetPasswordValidationFunctions.checkIfPassWordIsValid(password) != 0) {
                when(ResetPasswordValidationFunctions.checkIfPassWordIsValid(password)){
                    1 -> {
                        binding.editTextRegUserPassword.error = "password must be more than 8"
                        binding.editTextRegUserPassword.isFocusable
                    }
                    2 -> {
                        binding.editTextRegUserPassword.error = "password must contain number"
                        binding.editTextRegUserPassword.isFocusable
                    }
                    3 -> {
                        binding.editTextRegUserPassword.error = "password must contain special character"
                        binding.editTextRegUserPassword.isFocusable
                    }
                    4 -> {
                        binding.editTextRegUserPassword.error = "password must contain at least 1 lowercase alphabet"
                        binding.editTextRegUserPassword.isFocusable
                    }
                    5 -> {
                        binding.editTextRegUserPassword.error = "password must contain at least 1 uppercase alphabet"
                        binding.editTextRegUserPassword.isFocusable
                    }
                }
                return@setOnClickListener
            }


            if (!RegistrationValidation.validateRadioButtonIsChecked(radioButton)) {
                radioButton.error = "Agree to the terms and condition"
                radioButton.requestFocus()
                return@setOnClickListener

            }

            viewModel.registerUser(userDataTest)

        }

            binding.tvPrivacyPolicy.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_registrationFragment_to_privacyPolicyFragment)
            }

        }

    private fun registrationResponseObserver() {

        viewModel.newUser.observe(viewLifecycleOwner, {

            when(it){
                is ApiCallNetworkResource.Success ->{
                    binding.viewCover.visibility = View.GONE
                    binding.registerProgressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    val action = RegistrationFragmentDirections
                        .actionRegistrationFragmentToRegistrationIsSuccessfulFragment(userDataTest)
                    findNavController().navigate(action)
                }
                is ApiCallNetworkResource.Error ->{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    binding.viewCover.visibility = View.GONE
                    binding.registerProgressBar.visibility = View.GONE
                }
                is ApiCallNetworkResource.Loading ->{
                    binding.viewCover.visibility = View.VISIBLE
                    binding.registerProgressBar.visibility = View.VISIBLE

                }
            }

        })

    }
    private fun doThisWhenNetworkIsLost() {
        binding.regnetworkErrorMessage.visibility= View.VISIBLE
        binding.btnRegister.isEnabled = false
        binding.registerProgressBar.visibility = View.GONE
        binding.viewCover.visibility = View.GONE
    }

    private fun doThisWhenNetworkIsAvailable() {
        binding.regnetworkErrorMessage.visibility=View.GONE
        binding.btnRegister.isEnabled = true

    }
}

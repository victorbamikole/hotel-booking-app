package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentRegistrationBinding
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.viewModel.UIViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Response
import okhttp3.ResponseBody
import java.lang.ProcessBuilder.Redirect.to
import java.util.Observer

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private lateinit var _binding : FragmentRegistrationBinding
    private val binding get() = _binding
    private val viewModel: UIViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // var firstName = binding.tvRegUserName.text.toString()
        //var lastName = binding.tvRegUserName.text.toString()
        var email = binding.tvRegUserEmail.text.toString()
        //var userName = binding.tvRegUserName.text.toString()
        var password = binding.regPasswordInput.editText!!.text.toString()
        // var phoneNumber = binding.regPasswordInput.editText!!.text.toString()
        // var gender = binding.regPasswordInput.editText!!.text.toString()
        // var age = binding.regPasswordInput.editText!!.text.toString()
        var radioButton = binding.btnRadio

        var userData: UserDataResponseItem = UserDataResponseItem("ungbede","Onum",
            "purityezra@gmail.com","Sammycool1","Password14$",
            "08091185347","male", 23 )

        //Set click listener on login link
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {
//            if (Auth.validateUserName(userName.text.toString())){
//                userName.error = "User Name field must not be empty"
//                userName.isFocusable
//                return@setOnClickListener
//            }
//
//            if (Auth.validateEmail(email.text.toString())){
//                email.error = "Email field must not be empty"
//                email.isFocusable
//                return@setOnClickListener
//            }
//
//            if (Auth.validatePassword(password!!.text.toString())){
//                password.error = "Enter a valid password"
//                password.isFocusable
//                return@setOnClickListener
//            }
//
//            if(Auth.validateRadioButtonIsChecked(radioButton)){
//                radioButton.error = "Agree to the terms and condition"
//                radioButton.requestFocus()
//                return@setOnClickListener
//            }

            viewModel.registerUser(userData)
            viewModel.newUser.observe(requireActivity(), androidx.lifecycle.Observer { response ->
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

package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.MainActivity
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentLoginBinding
import com.example.hbapplicationgroupb.di.application.HotelApplication.Companion.application
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.util.BackPressedListener
import com.example.hbapplicationgroupb.util.resource.ConnectivityLiveData
import com.example.hbapplicationgroupb.validation.LoginValidation
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val roomViewModel: RoomViewModel by viewModels()
    private lateinit var connectivityLiveData: ConnectivityLiveData


    private lateinit var listener:BackPressedListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as BackPressedListener
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        connectivityLiveData = ConnectivityLiveData(application)

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        binding.tvUserLoginPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        initialiseNetworkObservers()

        //Toggle enable sign up button
        binding.tvUserLoginEmail.addTextChangedListener(loginButtonHandler)
        binding.tvUserPassword.addTextChangedListener(loginButtonHandler)




        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            listener.onBackPressedFromFragment()
            }
        })

    }

    //Observe live data from view model
    private fun loginNetworkObserver(){
        roomViewModel.userLoginDetails.observe(viewLifecycleOwner,  {
            if (it ==null) {
                binding.loadingView.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                Snackbar.make(
                    binding.root,
                    "Login failed; Invalid email address or password.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            else {
                findNavController().navigate(R.id.action_loginFragment_to_exploreFragment2)
                Snackbar.make(binding.root, "Login successful", Snackbar.LENGTH_LONG).show()
                binding.loadingView.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun clickBtnNetworkObserver(){
        binding.btnLogin.setOnClickListener {
            binding.loadingView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.VISIBLE
            login()
        }
    }

    private fun initialiseNetworkObservers(){
        connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
            when(isAvailable){
                true -> {
                    binding.networkConnectionTextId.visibility = View.GONE
                    binding.btnLogin.setOnClickListener {
                        loginNetworkObserver()
                        clickBtnNetworkObserver()

                    }
                }
                false -> {
                    binding.networkConnectionTextId.visibility = View.VISIBLE
                    binding.btnLogin.setOnClickListener {
                        Snackbar.make(
                            binding.root,
                            "Please check your internet connection",
                            Snackbar.LENGTH_LONG
                        ).show()
                        return@setOnClickListener
                    }
                }
            }
        }
        )
    }

    private fun login() {
        val usersEmail = binding.tvUserLoginEmail.text.toString().trim()
        val usersPassword = binding.tvUserPassword.text.toString().trim()
        if(LoginValidation.validateEmailPattern(usersEmail)){
            if (LoginValidation.validatePasswordPattern(usersPassword)){
                roomViewModel.sendUserLoginDetailsToApi(PostLoginUserData(usersEmail, usersPassword))
            }
            else{
                binding.regPasswordInput.error = "Password does not match with any email address"
                Snackbar.make(binding.root, "Invalid password", Snackbar.LENGTH_LONG).show()
                binding.regPasswordInput.error = null
                binding.loadingView.visibility = View.GONE
                binding.progressBar.visibility = View.GONE

            }
        }
        else{
            binding.regEmailInput.error = "Invalid email address"
            Snackbar.make(binding.root, "Invalid email address", Snackbar.LENGTH_LONG).show()
            binding.regPasswordInput.error = null
            binding.loadingView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE

        }

    }


    //Login button handler
    // If the two text fields are empty, the login button will be disabled
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

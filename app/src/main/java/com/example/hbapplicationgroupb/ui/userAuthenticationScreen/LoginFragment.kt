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
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentLoginBinding
import com.example.hbapplicationgroupb.di.application.HotelApplication.Companion.application
import com.example.hbapplicationgroupb.model.loginUserData.PostLoginUserData
import com.example.hbapplicationgroupb.util.BackPressedListener
import com.example.hbapplicationgroupb.util.constants.DEFAULT_TOKEN
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
    private var isNetworkAvailable : Boolean = true


    private lateinit var listener:BackPressedListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as BackPressedListener
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStart() {
        super.onStart()
        navigateToExploreScreen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        binding.tvUserLoginPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        initiateViewModel()

        connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
            when (isAvailable) {
                true -> {
                    binding.networkConnectionTextId.visibility = View.GONE
                    isNetworkAvailable = true
                }
                false -> {
                    binding.networkConnectionTextId.visibility = View.VISIBLE
                    isNetworkAvailable = false
                }
            }
        })

        //Toggle enable sign in button
        binding.tvUserLoginEmail.addTextChangedListener(loginButtonHandler)
        binding.tvUserPassword.addTextChangedListener(loginButtonHandler)

        binding.btnLogin.setOnClickListener {
            if(isNetworkAvailable){
                loadingStateVisibilityHandler()
                login()
            }
            else{
                Snackbar.make(
                    binding.root,
                    "Login in failed: Ensure you are have internet connection and try again",
                    Snackbar.LENGTH_LONG
                ).show()
            }

        }

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            listener.onBackPressedFromFragment()
            }
        })

    }

    //Observe live data in view model
    private fun initiateViewModel(){
        roomViewModel.userLoginDetails.observe(viewLifecycleOwner,  {
            if (it ==null) {
                loadingStateInvisibilityHandler()
                Snackbar.make(
                    binding.root, "Login failed; Invalid email address or password.", Snackbar.LENGTH_LONG
                ).show()
            }
            else {
                activity?.let { it1 ->
                    UserPreferences(it1).saveSession(it.data.token,it.data.id,it.data.refreshToken)
                }
                findNavController().navigate(R.id.action_loginFragment_to_exploreFragment2)
                Snackbar.make(binding.root, "Login successful", Snackbar.LENGTH_LONG).show()
                loadingStateInvisibilityHandler()
            }
        })
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
                loadingStateInvisibilityHandler()
            }
        }
        else{
            binding.regEmailInput.error = "Invalid email address"
            Snackbar.make(binding.root, "Invalid email address", Snackbar.LENGTH_LONG).show()
            binding.regPasswordInput.error = null
            loadingStateInvisibilityHandler()

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

    //Navigate to Explore Screen
    private fun navigateToExploreScreen(){
        //check if user is already logged in and move to app if true
        val userSession = activity?.let { UserPreferences(it).getUserId() }
        if (userSession != DEFAULT_TOKEN){
            //Move into the App
            findNavController().navigate(R.id.action_loginFragment_to_exploreFragment2)

        }
    }

    private fun loadingStateVisibilityHandler(){
        binding.loadingView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun loadingStateInvisibilityHandler(){
        binding.loadingView.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }
}

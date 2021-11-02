package com.example.hbapplicationgroupb

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentEditUserProfileBinding
import com.example.hbapplicationgroupb.di.application.HotelApplication
import com.example.hbapplicationgroupb.model.updateUserData.PostUpdateUserData
import com.example.hbapplicationgroupb.model.userData.Data
import com.example.hbapplicationgroupb.validation.UpdateUserValidation
import com.example.hbapplicationgroupb.util.resource.ApiCallNetworkResource
import com.example.hbapplicationgroupb.util.resource.ConnectivityLiveData
import com.example.hbapplicationgroupb.util.resource.observeNetworkConnection
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditUserProfile : Fragment(R.layout.fragment_edit_user_profile) {

    var binding: FragmentEditUserProfileBinding? = null
    private val viewModel: RoomViewModel by viewModels()
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private  lateinit var updatedUserData: PostUpdateUserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(HotelApplication.application)


      // var token = activity?.let { UserPreferences(it).getUserToken()}

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditUserProfileBinding.bind(view)
        val userToken = activity?.let { UserPreferences(it).getUserToken() }

        viewModel.getUserProfile( "Bearer $userToken")
        viewModel.userProfile.observe(viewLifecycleOwner, Observer { response ->
            if(response.succeeded){
              //  Log.d(TAG, "onCreate: $response")
                var userProfile : Data = response.data
                binding?.EditProfileFragmentEditTextViewFirstName?.setText(userProfile.firstName)
                binding?.EditProfileFragmentEditTexLastName?.setText(userProfile.lastName)
                binding?.EditProfileFragmentEditTextPhoneNumber?.setText(userProfile.phoneNumber)
                binding?.EditProfileFragmentEditTextAddress?.setText(userProfile.address)
                binding?.EditProfileFragmentEditTextState?.setText(userProfile.state)
            }
        })


        //observe network state
        observeNetworkConnection(connectivityLiveData,viewLifecycleOwner,
            { doThisWhenNetworkIsAvailable() }, { doThisWhenNetworkIsLost() })

        registrationResponseObserver()

        binding?.EditProfileFragmentUpdateButton?.setOnClickListener {
                //userToken = activity?.let { UserPreferences(it).getUserToken() }
            val updatedFirstName = binding?.EditProfileFragmentEditTextViewFirstName?.text?.trim().toString()
            val updatedLastName = binding?.EditProfileFragmentEditTexLastName?.text?.trim().toString()
            val updatedPhoneNumber = binding?.EditProfileFragmentEditTextPhoneNumber?.text?.trim().toString()
            val updatedAddress = binding?.EditProfileFragmentEditTextAddress?.text?.trim().toString()
            val updatedState = binding?.EditProfileFragmentEditTextState?.text?.trim().toString()

            updatedUserData = PostUpdateUserData(updatedFirstName, updatedLastName
                ,updatedPhoneNumber,20,"5399834540157534",
                updatedAddress,updatedState, "2021-10-25T20:38:57.794Z")

            if (!UpdateUserValidation.validateUpdatedUserFirstName(updatedFirstName)) {
                binding?.EditProfileFragmentEditTextViewFirstName?.error = "Enter a First Name"
                binding?.EditProfileFragmentEditTextViewFirstName?.isFocusable
                return@setOnClickListener
            }

            if (!UpdateUserValidation.validateUpdatedUserLastName(updatedLastName)) {
                binding?.EditProfileFragmentEditTexLastName?.error = "Enter a Last Name"
                binding?.EditProfileFragmentEditTexLastName?.isFocusable
                return@setOnClickListener
            }

            if (!UpdateUserValidation.validateUpdatedUserPhoneNumber(updatedPhoneNumber)) {
                binding?.EditProfileFragmentEditTextPhoneNumber?.error = "Enter a Phone Number"
                binding?.EditProfileFragmentEditTextPhoneNumber?.isFocusable
                return@setOnClickListener
            }

            if (!UpdateUserValidation.validateUpdatedUserAddress(updatedAddress)) {
                binding?.EditProfileFragmentEditTextAddress?.error = "Enter a valid Address"
                binding?.EditProfileFragmentEditTextAddress?.isFocusable
                return@setOnClickListener
            }

            if (!UpdateUserValidation.validateUpdatedUserState(updatedState)) {
                binding?.EditProfileFragmentEditTextState?.error = "Enter a valid State"
                binding?.EditProfileFragmentEditTextState?.isFocusable
                return@setOnClickListener
            }

            viewModel.updateUserDetails(updatedUserData, "Bearer $userToken")
        }
    }

    private fun registrationResponseObserver() {
        viewModel.updatedDetails.observe(viewLifecycleOwner, {
            when(it){
                is ApiCallNetworkResource.Success ->{
                    binding?.editProfileViewCover?.visibility = View.GONE
                    binding?.editProfileProgressBar?.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    val action = EditUserProfileDirections.actionEditUserProfileToProfileFragment2()
                    findNavController().navigate(action)
                }
                is ApiCallNetworkResource.Error ->{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    binding?.editProfileViewCover?.visibility = View.GONE
                    binding?.editProfileProgressBar?.visibility = View.GONE
                }
                is ApiCallNetworkResource.Loading ->{
                    binding?.editProfileViewCover?.visibility = View.VISIBLE
                    binding?.editProfileProgressBar?.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun doThisWhenNetworkIsLost() {
        binding?.editProfileNetworkErrorMessage?.visibility= View.VISIBLE
        binding?.EditProfileFragmentUpdateButton?.isEnabled = false
        binding?.editProfileProgressBar?.visibility = View.GONE
        binding?.editProfileViewCover?.visibility = View.GONE
    }

    private fun doThisWhenNetworkIsAvailable() {
        binding?.editProfileNetworkErrorMessage?.visibility=View.GONE
        binding?.EditProfileFragmentUpdateButton?.isEnabled = true

    }

}
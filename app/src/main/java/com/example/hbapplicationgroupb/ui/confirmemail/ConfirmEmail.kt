package com.example.hbapplicationgroupb.ui.confirmemail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentConfirmEmailBinding
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmEmail : Fragment(R.layout.fragment_confirm_email) {
    private lateinit var binding:FragmentConfirmEmailBinding
    private val roomViewModel: RoomViewModel by viewModels()
    private lateinit var emailAndToken:ConfirmEmailAddress

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConfirmEmailBinding.bind(view)

        //received email and token passed from deep linking

        val userToken = arguments?.getString("emailConfirmationToken")!!
        val userEmail = arguments?.getString("emailConfirmation")!!

        //Assigning it to confirm email address dataclass
        emailAndToken = ConfirmEmailAddress(
            email = userEmail,
            token = userToken
        )


        roomViewModel.confirmEmailAddress.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                findNavController().navigate(R.id.action_confirmEmail_to_loginFragment)
                Snackbar.make(binding.root, "Email verification successful", Snackbar.LENGTH_LONG).show()
            }
            else{
                Snackbar.make(
                    binding.root,
                    "An error occurred; Expired token, please try to register again",
                    Snackbar.LENGTH_LONG
                ).show()
            }

        })

        //attach an onclick listener to the verify me button to trigger the network call
        binding.fragmentConfirmationScreenBackButton.setOnClickListener {
            verifyMe()
        }
    }

    //Make network call to the confirm email end point to verify user
    private fun verifyMe(){
        roomViewModel.confirmEmailAddress(emailAndToken)
    }
}
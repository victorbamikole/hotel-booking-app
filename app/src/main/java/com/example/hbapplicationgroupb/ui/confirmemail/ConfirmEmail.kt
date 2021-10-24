package com.example.hbapplicationgroupb.ui.confirmemail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentConfirmEmailBinding
import com.example.hbapplicationgroupb.model.emailconfirmation.ConfirmEmailAddress
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmEmail : Fragment(R.layout.fragment_confirm_email) {
    private lateinit var binding:FragmentConfirmEmailBinding
    private val roomViewModel: RoomViewModel by viewModels()
    private lateinit var emailAndToken:ConfirmEmailAddress

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConfirmEmailBinding.bind(view)

        val userToken = arguments?.getString("emailConfirmationToken")!!
        val userEmail = arguments?.getString("emailConfirmation")!!

            emailAndToken = ConfirmEmailAddress(
                email = userEmail,
                token = userToken
            )

        roomViewModel.confirmEmailAddress(emailAndToken)

        roomViewModel.confirmEmailAddress.observe(viewLifecycleOwner,{
            if(it != null){
                binding.errorTextViewId.visibility = View.GONE
            }
            else{
                binding.errorTextViewId.visibility = View.VISIBLE
                binding.verifyMeText.visibility = View.GONE
                binding.fragmentConfirmationScreenBackButton.visibility = View.GONE
            }
        })

        binding.fragmentConfirmationScreenBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_confirmEmail_to_loginFragment)
        }
    }

}
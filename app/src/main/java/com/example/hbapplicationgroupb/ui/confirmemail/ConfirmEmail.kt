package com.example.hbapplicationgroupb.ui.confirmemail

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
//    private val args:ConfirmEmailArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConfirmEmailBinding.bind(view)
       initView()

        val userToken = arguments?.getString("emailConfirmationToken")!!
        val userEmail = arguments?.getString("emailConfirmation")!!

            emailAndToken = ConfirmEmailAddress(
                email = userEmail,
                token = userToken
            )
        Toast.makeText(requireContext(),"email and token is $emailAndToken",Toast.LENGTH_SHORT)
            .show()

        roomViewModel.confirmEmailAddress(emailAndToken)

        roomViewModel.confirmEmailAddress.observe(viewLifecycleOwner,{
            if(it.succeeded){
                binding.fragmentEmailConfirmationErrorTextview.visibility = View.GONE
                binding.emailConfirmationProgressBar.visibility = View.GONE
                binding.fragmentEmailCongratulationTextView.visibility = View.VISIBLE
                binding.fragmentEmailHaveBeenConfirmedTextview.visibility = View.VISIBLE
            }else{
                binding.fragmentEmailConfirmationImage.visibility = View.GONE
                binding.fragmentEmailConfirmationErrorTextview.visibility = View.VISIBLE
                binding.emailConfirmationProgressBar.visibility = View.GONE
                binding.fragmentEmailCongratulationTextView.visibility = View.GONE
                binding.fragmentEmailHaveBeenConfirmedTextview.visibility = View.GONE
            }
        })

        binding.fragmentConfirmationScreenBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_confirmEmail_to_loginFragment)
        }
    }

    private fun initView() {
        binding.emailConfirmationProgressBar.visibility = View.VISIBLE
        binding.fragmentEmailConfirmationImage.visibility = View.GONE
        binding.fragmentEmailConfirmationErrorTextview.visibility = View.GONE
        binding.fragmentEmailCongratulationTextView.visibility = View.GONE
        binding.fragmentEmailHaveBeenConfirmedTextview.visibility = View.GONE
    }
}
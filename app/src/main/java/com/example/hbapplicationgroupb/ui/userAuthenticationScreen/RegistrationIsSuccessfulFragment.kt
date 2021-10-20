package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentRegistrationIsSuccessfulBinding
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.bookingDetailsScreenFragment.BookingDetailsScreenFragmentArgs
import com.example.hbapplicationgroupb.viewModel.RoomViewModel


class RegistrationIsSuccessfulFragment : Fragment(R.layout.fragment_registration_is_successful) {
    private lateinit var binding:FragmentRegistrationIsSuccessfulBinding
    private val safeArgs : RegistrationIsSuccessfulFragmentArgs by navArgs()
    private val viewModel: RoomViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationIsSuccessfulBinding.bind(view)
        val userData = safeArgs.userData

        binding.emailFromSafeArgs.text = userData.email
        registrationResponseObserver()

        binding.resendMail.setOnClickListener {
            viewModel.registerUser(userData)
        }
        binding.goToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationIsSuccessfulFragment_to_loginFragment)
        }
    }

    private fun registrationResponseObserver() {

        viewModel.newUser.observe(viewLifecycleOwner, {
            if (it != null) {
                if (it.succeeded){
                    binding.viewCover.visibility = View.GONE
                    binding.registerProgressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_LONG).show()
            }
        })

    }
}
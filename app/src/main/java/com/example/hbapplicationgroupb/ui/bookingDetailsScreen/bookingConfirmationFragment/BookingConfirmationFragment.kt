package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.bookingConfirmationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentBookingConfirmationBinding
import com.example.hbapplicationgroupb.model.hotelBooking.verifyBooking.VerifyBookingData
import com.example.hbapplicationgroupb.ui.paymentcheckout.PaymentCheckoutFragmentArgs
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingConfirmationFragment : Fragment(R.layout.fragment_booking_confirmation) {
    private lateinit var binding: FragmentBookingConfirmationBinding
    private  val confirmBookingSaveArgs: BookingConfirmationFragmentArgs by navArgs()
    private val roomViewModel: RoomViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookingConfirmationBinding.bind(view)
        val token = activity?.let { it1 ->
            UserPreferences(it1).getUserToken()
        }

        val url = confirmBookingSaveArgs.url

        val splitUrl = url.split("=")
        val reference = splitUrl[splitUrl.lastIndex]
        val body = VerifyBookingData(reference,reference)
        roomViewModel.verifyPaymentFunc("Bearer $token",body)
        roomViewModel.verifyPayment.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })

        binding.fragmentConfirmationScreenBackButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_bookingConfirmationFragment_to_exploreFragment2)
        }
    }
}
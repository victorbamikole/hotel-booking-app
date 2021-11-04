package com.example.hbapplicationgroupb.ui.paymentcheckout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentPaymentCheckoutBinding
import com.example.hbapplicationgroupb.model.hotelBooking.HotelBookingDataWithPaymentType
import com.example.hbapplicationgroupb.util.resource.ApiCallNetworkResource
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentCheckoutFragment : Fragment(R.layout.fragment_payment_checkout) {
    private val roomViewModel : RoomViewModel by viewModels()
    var binding: FragmentPaymentCheckoutBinding? = null
    private lateinit var paymentDetail:HotelBookingDataWithPaymentType
        private  val paymentSaveArgs:PaymentCheckoutFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentCheckoutBinding.bind(view)

        val bookingDetails = paymentSaveArgs.bookingDetailObject
        "#${bookingDetails.price}".also { binding?.amountPrice?.text = it }

        observeBookHotelRoomFunction()

        val token = activity?.let {
            UserPreferences(it).getUserToken()
        }
        binding?.paymentByPayStackLayout?.setOnClickListener {
            binding?.viewCover?.visibility = View.VISIBLE
            binding?.registerProgressBar?.visibility = View.VISIBLE

            paymentDetail = HotelBookingDataWithPaymentType(
                roomId = bookingDetails.roomId,
                checkIn = bookingDetails.checkIn,
                checkOut = bookingDetails.checkOut,
                noOfPeople = bookingDetails.noOfPeople,
                paymentService = "paystack"
            )
            roomViewModel.bookAnHotel("Bearer $token",paymentDetail)
        }


    }

    private fun observeBookHotelRoomFunction() {
        roomViewModel.bookAnHotel.observe(viewLifecycleOwner,{
            when(it){
                is ApiCallNetworkResource.Success->{
                    binding?.viewCover?.visibility = View.GONE
                    binding?.registerProgressBar?.visibility = View.GONE
                    if (it.data != null){
                        navigateToBrowser(it.data.data.paymentUrl)
                    }

                }
                is ApiCallNetworkResource.Error->{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    binding?.viewCover?.visibility = View.GONE
                    binding?.registerProgressBar?.visibility = View.GONE
                }
                is ApiCallNetworkResource.Loading->{
                    binding?.viewCover?.visibility = View.VISIBLE
                    binding?.registerProgressBar?.visibility = View.VISIBLE
                    binding?.paymentByPayStackLayout?.isEnabled = false
                }
            }
        })
    }

    private fun navigateToBrowser(data:String) {
//        val defaultBrowser =
//            Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
//        defaultBrowser.data = Uri.parse(data)
//        startActivity(defaultBrowser)
        val action = PaymentCheckoutFragmentDirections.actionPaymentCheckoutFragmentToWebViewFragment(data)
        findNavController().navigate(action)
    }

}
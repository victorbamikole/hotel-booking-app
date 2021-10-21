package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.bookingDetailsScreenFragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.aminography.primecalendar.PrimeCalendar
import androidx.navigation.fragment.navArgs
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.RangeDaysPickCallback
import com.aminography.primedatepicker.picker.callback.SingleDayPickCallback
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentBookingDetailsScreenBinding
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BootomSheetInterface.AgeBracketListenerInterface
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetAgeBracket.BottomSheetForAgeBracket
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetForRooms.BottomSheetForRooms
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.example.hbapplicationgroupb.ui.hoteldescription.HotelDescriptionFragmentArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingDetailsScreenFragment : Fragment(R.layout.fragment_booking_details_screen), AgeBracketListenerInterface {
    private lateinit var binding: FragmentBookingDetailsScreenBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private val safeArgs : BookingDetailsScreenFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookingDetailsScreenBinding.bind(view)

        val hotelName = safeArgs.hotelName

        binding.bookingDetailsScreenTextViewName.setText(hotelName)
        /** Method to pop bottom Sheet for Calender Start-date EditTexView */
        binding.bookingDetailsScreenTextViewStartDate.setOnClickListener {
            val rangeDaysPickCallback = RangeDaysPickCallback { startDay, endDay ->
                binding.bookingDetailsScreenTextViewStartDate.setText(startDay.longDateString)
                binding.bookingDetailsScreenTextViewEndDate.setText(endDay.longDateString)
            }
            val today = CivilCalendar()
            val datePicker = PrimeDatePicker.bottomSheetWith(today)
                .pickRangeDays(rangeDaysPickCallback)
                .build()
                 fragmentManager?.let { datePicker.show(it, "SOME_TAG")}
        }


        /** Method to pop bottom Sheet for Calender End-date EditTexView */
        binding.bookingDetailsScreenTextViewEndDate.setOnClickListener{
            val rangeDaysPickCallback = RangeDaysPickCallback { startDay, endDay ->
                binding.bookingDetailsScreenTextViewStartDate.setText(startDay.longDateString)
                binding.bookingDetailsScreenTextViewEndDate.setText(endDay.longDateString)
            }
            val today = CivilCalendar()
            val datePicker = PrimeDatePicker.bottomSheetWith(today)
                .pickRangeDays(rangeDaysPickCallback)
                .build()
            fragmentManager?.let { datePicker.show(it, "SOME_TAG")
        }
    }


        binding.fragmentBookingBookNowButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_bookingDetailsScreenFragment2_to_paymentCheckoutFragment)
        }
        binding.fragmentBookingDetailsBackArrowViev.setOnClickListener {
            findNavController()
                .navigate(R.id.action_bookingDetailsScreenFragment2_to_exploreFragment2)
        }


        /** Method to pop bottom Sheet for Age Selection */
        binding.bookingDetailsScreenTextViewAgeBracket.setOnClickListener {
            val bottomSheet : BottomSheetForAgeBracket = BottomSheetForAgeBracket(this)
            fragmentManager?.let { it1 -> bottomSheet.show(it1, "TAG")}
        }


        /** Method to pop bottom Sheet for Room type Selection */
        binding.bookingDetailsScreenTextViewRoomType.setOnClickListener {
            val bottomSheet : BottomSheetForRooms = BottomSheetForRooms()
            fragmentManager?.let { it1 -> bottomSheet.show(it1, "TAG")}
        }


        binding.fragmentBookingBookNowButton.setOnClickListener {
            val nameForBooking = binding.bookingDetailsScreenTextViewName.text.toString()
            val phoneNumberForBooking = binding.bookingDetailsScreenTextViewPhoneNumber.text.toString()
            val checkIn = binding.bookingDetailsScreenTextViewStartDate.text.toString()
            val checkout = binding.bookingDetailsScreenTextViewEndDate.text.toString()
            val roomType = binding.bookingDetailsScreenTextViewRoomType.text.toString()
        }
}


    override fun OnclickOfDoneTextView(numberOfPersons: String) {
        binding.bookingDetailsScreenTextViewAgeBracket.setText(numberOfPersons)
    }
}
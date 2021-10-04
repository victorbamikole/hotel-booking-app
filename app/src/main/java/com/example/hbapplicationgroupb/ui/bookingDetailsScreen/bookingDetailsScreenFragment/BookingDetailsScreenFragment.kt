package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.bookingDetailsScreenFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.SingleDayPickCallback
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentBookingDetailsScreenBinding
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetAgeBracket.BottomSheetForAgeBracket
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetForRooms.BottomSheetForRooms
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingDetailsScreenFragment : Fragment(R.layout.fragment_booking_details_screen) {
    private lateinit var binding: FragmentBookingDetailsScreenBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookingDetailsScreenBinding.bind(view)


        /** Method to pop bottom Sheet for Calender Start-date EditTexView */
        binding.bookingDetailsScreenTextViewStartDate.setOnClickListener {
            val callback = SingleDayPickCallback { day ->
                // TODO
            }
            val today = CivilCalendar()
            val datePicker = PrimeDatePicker.bottomSheetWith(today)
                .pickRangeDays()
                .build()
                 fragmentManager?.let { datePicker.show(it, "SOME_TAG") }
        }


        /** Method to pop bottom Sheet for Calender End-date EditTexView */
        binding.bookingDetailsScreenTextViewEndDate.setOnClickListener{
            val callback = SingleDayPickCallback { day ->
                // TODO
            }
            val today = CivilCalendar()
            val datePicker = PrimeDatePicker.bottomSheetWith(today)
                .pickRangeDays()
                .build()
            fragmentManager?.let { datePicker.show(it, "SOME_TAG")
        }
    }
        binding.fragmentBookingBookNowButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_bookingDetailsScreenFragment2_to_bookingConfirmationFragment)
        }


        /** Method to pop bottom Sheet for Age Selection */
        binding.bookingDetailsScreenTextViewAgeBracket.setOnClickListener {
            var bottomSheet : BottomSheetForAgeBracket = BottomSheetForAgeBracket()
            fragmentManager?.let { it1 -> bottomSheet.show(it1, "TAG") }
        }


        /** Method to pop bottom Sheet for Room type Selection */
        binding.bookingDetailsScreenTextViewRoomType.setOnClickListener {
            var bottomSheet : BottomSheetForRooms = BottomSheetForRooms()
            fragmentManager?.let { it1 -> bottomSheet.show(it1, "TAG") }
        }
}
}
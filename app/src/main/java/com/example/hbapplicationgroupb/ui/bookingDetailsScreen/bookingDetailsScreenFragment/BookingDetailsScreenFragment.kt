package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.bookingDetailsScreenFragment

import android.os.Bundle
import android.util.SparseIntArray
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.common.BackgroundShapeType
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.RangeDaysPickCallback
import com.aminography.primedatepicker.picker.theme.LightThemeFactory
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentBookingDetailsScreenBinding
import com.example.hbapplicationgroupb.model.customerBookingData.CustomerBookingDataItem
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BootomSheetInterface.AgeBracketListenerInterface
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BootomSheetInterface.RoomTypeListenerInterface
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetAgeBracket.BottomSheetForAgeBracket
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetForRooms.BottomSheetForRooms
import com.example.hbapplicationgroupb.validation.BookingDetailsValidation
import com.example.hbapplicationgroupb.validation.RegistrationValidation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class BookingDetailsScreenFragment : Fragment(R.layout.fragment_booking_details_screen), AgeBracketListenerInterface , RoomTypeListenerInterface{
    private lateinit var binding: FragmentBookingDetailsScreenBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private val safeArgs : BookingDetailsScreenFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookingDetailsScreenBinding.bind(view)
        val hotelName = safeArgs.hotelName
        binding.bookingDetailsScreenTextViewName.setText(hotelName)

        lateinit var datePicker : PrimeDatePicker
        /** Method to pop bottom Sheet for Calender Start-date EditTexView */
        binding.bookingDetailsScreenTextViewStartDate.setOnClickListener {
            val today = CivilCalendar()
             datePicker = PrimeDatePicker.bottomSheetWith(today)
                .pickRangeDays{ startDay, endDay ->
                    binding.bookingDetailsScreenTextViewStartDate.setText(startDay.longDateString)
                    binding.bookingDetailsScreenTextViewEndDate.setText(endDay.longDateString)
                }.applyTheme(themeFactory).build()
            datePicker.show(parentFragmentManager, "dateRange")
        }



        /** Method to pop bottom Sheet for Calender End-date EditTexView */
        binding.bookingDetailsScreenTextViewEndDate.setOnClickListener{
            val today = CivilCalendar()
            datePicker = PrimeDatePicker.bottomSheetWith(today)
                .pickRangeDays{ startDay, endDay ->
                    binding.bookingDetailsScreenTextViewStartDate.setText(startDay.longDateString)
                    binding.bookingDetailsScreenTextViewEndDate.setText(endDay.longDateString)
                }.applyTheme(themeFactory).build()
            datePicker.show(parentFragmentManager, "dateRange")
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
            val bottomSheet : BottomSheetForRooms = BottomSheetForRooms(this)
            fragmentManager?.let { it1 -> bottomSheet.show(it1, "TAG")}
        }


        binding.fragmentBookingBookNowButton.setOnClickListener {
            val nameForBooking = binding.bookingDetailsScreenTextViewName.text.toString()
            val phoneNumberForBooking = binding.bookingDetailsScreenTextViewPhoneNumber.text.toString()
            val checkIn = binding.bookingDetailsScreenTextViewStartDate.text.toString()
            val checkout = binding.bookingDetailsScreenTextViewEndDate.text.toString()
            val people = binding.bookingDetailsScreenTextViewAgeBracket.text.toString()
            val roomType = binding.bookingDetailsScreenTextViewRoomType.text.toString()


            /**Was about creating a booking details Data when I noticed that the endPoint is
             * not the same as the given UI information*/
           // val bookingDetails = CustomerBookingDataItem (1, checkIn, checkout, people,)
            if (!BookingDetailsValidation.validateName(nameForBooking)) {
                binding.bookingDetailsScreenTextViewName.error = "A name must be entered"
                binding.bookingDetailsScreenTextViewName.isFocusable
                return@setOnClickListener
            }



            /**Validation for Booking details Fields*/
            if (!BookingDetailsValidation.validatePhoneNumber(phoneNumberForBooking)) {
                binding.bookingDetailsScreenTextViewPhoneNumber.error = "Enter a Phone Number"
                binding.bookingDetailsScreenTextViewPhoneNumber.isFocusable
                return@setOnClickListener
            }

            if (!BookingDetailsValidation.validateStartDate(checkIn)) {
                binding.bookingDetailsScreenTextViewStartDate.error = "Enter a Start Date"
                binding.bookingDetailsScreenTextViewStartDate.isFocusable
                return@setOnClickListener
            }


            if (!BookingDetailsValidation.validateEndDate(checkout)) {
                binding.bookingDetailsScreenTextViewEndDate.error = "Enter an End Date"
                binding.bookingDetailsScreenTextViewEndDate.isFocusable
                return@setOnClickListener
            }


            if (!BookingDetailsValidation.validateAgeBracket(people)) {
                binding.bookingDetailsScreenTextViewAgeBracket.error = "Enter the number of persons"
                binding.bookingDetailsScreenTextViewAgeBracket.isFocusable
                return@setOnClickListener
            }

            if (!BookingDetailsValidation.validateRoomType(roomType)) {
                binding.bookingDetailsScreenTextViewRoomType.error = "Select a room type"
                binding.bookingDetailsScreenTextViewRoomType.isFocusable
                return@setOnClickListener
            }

        }
}


    override fun OnclickOfDoneTextView(numberOfPersons: String) {
        binding.bookingDetailsScreenTextViewAgeBracket.setText(numberOfPersons)
    }

    override fun OnclickOfDoneTextViewRoomTypes(selectedRooms: String) {
        binding.bookingDetailsScreenTextViewRoomType.setText(selectedRooms)
    }


    private val themeFactory = object : LightThemeFactory() {
        override val pickedDayBackgroundShapeType: BackgroundShapeType
            get() = BackgroundShapeType.CIRCLE
        override val calendarViewPickedDayInRangeBackgroundColor: Int
           get() = getColor(R.color.custom_orange)

        override val actionBarTodayTextColor: Int
            get() = getColor(R.color.white)

        override val actionBarNegativeTextColor: Int
            get() = getColor(R.color.black)

        override val actionBarPositiveTextColor: Int
            get() = getColor(R.color.lightButtonBarNegativeTextColor)

        override val calendarViewMonthLabelTextColor: Int
            get() = getColor(R.color.white)

        override val calendarViewShowAdjacentMonthDays : Boolean
            get() = true

        override val selectionBarBackgroundColor : Int
            get() = getColor(R.color.custom_orange)

        override val selectionBarRangeDaysItemBackgroundColor: Int
            get() = getColor(R.color.custom_orange)
    }

}
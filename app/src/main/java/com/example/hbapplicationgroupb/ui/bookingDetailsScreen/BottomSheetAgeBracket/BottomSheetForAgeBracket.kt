package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetAgeBracket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetForAgeBracket: BottomSheetDialogFragment() {
    private val viewModel: RoomViewModel by activityViewModels()
    lateinit var cancelText : TextView
    lateinit var doneText : TextView

    lateinit var adultText : TextView
    lateinit var adultCount : TextView
    lateinit var addAdultsButton : View
    lateinit var subtractAdult : View

    lateinit var teenText : TextView
    lateinit var teensCount : TextView
    lateinit var addTeenButton : View
    lateinit var subtractTeen : View

    lateinit var childrenText : TextView
    lateinit var childrenCount : TextView
    lateinit var addChildrenButton : View
    lateinit var subtractChildren : View

    lateinit var infantText : TextView
    lateinit var infantCount : TextView
    lateinit var addInfantButton : View
    lateinit var subtractInfant : View





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.age_bracket_bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var numAdult = 1
        var numTeens = 1
        var numChildren = 1
        var numInfant = 1


        cancelText = view.findViewById(R.id.fragment_bookingDetails_CancelText_textView)
        doneText = view.findViewById(R.id.fragment_booking_details_doneText_textView)

        adultText = view.findViewById(R.id.fragment_bookingDetails_AdultsText_textView)
        adultCount = view.findViewById(R.id.fragment_booking_details_Adult_count_TextView)
        addAdultsButton = view.findViewById(R.id.fragment_bookingDetails_AddAdults_Icon)
        subtractAdult = view.findViewById(R.id.fragment_booking_details_subtractAdults_view)

        teenText = view.findViewById(R.id.fragment_bookingDetails_TeensText_textView)
        teensCount = view.findViewById(R.id.fragment_bookingDetails_teensCount_textView)
        addTeenButton = view.findViewById(R.id.fragment_BookingDetails_AddTeens_View)
        subtractTeen = view.findViewById(R.id.fragment_bookingDetails_SubtractTeens_View)

        childrenText = view.findViewById(R.id.fragment_bookingDetails_ChildrenText_textView)
        childrenCount = view.findViewById(R.id.fragment_bookingDetails_ChildrenCount_textView)
        addChildrenButton = view.findViewById(R.id.fragment_bookingDetails_AddChildren_view)
        subtractChildren = view.findViewById(R.id.fragment_bookingDetails_SubtractChildren_view)

        infantText = view.findViewById(R.id.fragment_BookingDetails_InfantText_TextView)
        infantCount = view.findViewById(R.id.fragment_BookingDetails_InfantsCount_textView)
        addInfantButton = view.findViewById(R.id.fragment_BookingDetails_AddInfants_view)
        subtractInfant = view.findViewById(R.id.fragment_BookingDetails_SubtractInfants_view)




        /**Adults-Bottom Sheet Implementation for clicks and selection*/
        addAdultsButton.setOnClickListener{
           numAdult++
            adultCount.text = numAdult.toString()
        }

        subtractAdult.setOnClickListener{
            if (numAdult==1){
                Toast.makeText(requireContext(), "Value must not be less than one", Toast.LENGTH_SHORT).show()
            }
            else{
                numAdult--
                adultCount.text = numAdult.toString()
            }
        }


        /**Teens-Bottom Sheet Implementation for clicks and selection*/
        addTeenButton.setOnClickListener{
            numTeens++
            teensCount.text = numTeens.toString()
        }

        subtractTeen.setOnClickListener{
            if (numTeens==1){
                Toast.makeText(requireContext(), "Value must not be less than one", Toast.LENGTH_SHORT).show()
            }
            else{
                numTeens--
                teensCount.text = numTeens.toString()
            }
        }


        /**Children-Bottom Sheet Implementation for clicks and selection*/
        addChildrenButton.setOnClickListener{
            numChildren++
            childrenCount.text = numChildren.toString()
        }

        subtractChildren.setOnClickListener{
            if (numChildren==1){
                Toast.makeText(requireContext(), "Value must not be less than one", Toast.LENGTH_SHORT).show()
            }
            else{
                numChildren--
                childrenCount.text = numChildren.toString()
            }
        }


        /**Infant-Bottom Sheet Implementation for clicks and selection*/
        addInfantButton.setOnClickListener{
            numInfant++
            infantCount.text = numInfant.toString()
        }

        subtractInfant.setOnClickListener{
            if (numInfant==1){
                Toast.makeText(requireContext(), "Value must not be less than one", Toast.LENGTH_SHORT).show()
            }
            else{
                numInfant--
                infantCount.text = numInfant.toString()
            }
        }


        /**Selected Booking Details from Age bottom Sheet*/
        var ageBracket = "Selected Option"
        var numberOfPersons = 0


        adultText.setOnClickListener {
            ageBracket = "Adult"
            numberOfPersons = numAdult
            Toast.makeText(requireContext(), "$numberOfPersons $ageBracket selected", Toast.LENGTH_LONG).show()
        }

        childrenText.setOnClickListener {
            ageBracket = "Children"
            numberOfPersons = numChildren
            Toast.makeText(requireContext(), "$numberOfPersons $ageBracket selected", Toast.LENGTH_LONG).show()
        }

        teenText.setOnClickListener {
            ageBracket = "Teens"
            numberOfPersons = numTeens
            Toast.makeText(requireContext(), "$numberOfPersons $ageBracket selected", Toast.LENGTH_LONG).show()
        }

        infantText.setOnClickListener {
            ageBracket = "Infant"
            numberOfPersons = numInfant
            Toast.makeText(requireContext(), " $numberOfPersons $ageBracket selected", Toast.LENGTH_LONG).show()
        }


        cancelText.setOnClickListener {
            
        }

    }
}
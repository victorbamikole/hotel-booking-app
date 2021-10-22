package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetAgeBracket

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BootomSheetInterface.AgeBracketListenerInterface
import com.example.hbapplicationgroupb.validation.convertArrayToString
import com.example.hbapplicationgroupb.validation.removerWhereArrayContain
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetForAgeBracket(private val listener: AgeBracketListenerInterface): BottomSheetDialogFragment() {
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


        /**Observing the text for all counts*/
        observeAdultCount()
        observerTeensCount()
        observeChildrenCount()
        observeInfantCount()


        /**Selected Booking Details from Age bottom Sheet*/
        var personsBooked : ArrayList<String> = ArrayList(emptyList())
        var addedAdult = ""
        var addedTeen = ""
        var addedChildren = ""
        var addedInfant = ""


        /**Adults-Bottom Sheet Implementation for clicks and selection*/
        addAdultsButton.setOnClickListener{
            if(personsBooked.contains(addedAdult)){
                personsBooked.remove(addedAdult)
                viewModel.addToAdultCount()
                if(adultCount.text.toString().toInt()>1){
                    addedAdult = "${adultCount.text} Adults"
                } else{
                    addedAdult = "${adultCount.text} Adult"
                }
                personsBooked.add(addedAdult)
            }
            else{
                viewModel.addToAdultCount()
                if(adultCount.text.toString().toInt()>1){
                    addedAdult = "${adultCount.text} Adults"
                } else{
                    addedAdult = "${adultCount.text} Adult"
                }
                personsBooked.add(addedAdult)
            }
        }


        subtractAdult.setOnClickListener {
            if (adultCount.text.toString().toInt()==0){
                Toast.makeText(requireContext(), "Value must not be less than zero", Toast.LENGTH_SHORT).show()
            }

            else if (personsBooked.contains(addedAdult)){
                personsBooked.remove(addedAdult)
                viewModel.subtractFromAdultCount()
                if(adultCount.text.toString().toInt()>1){
                    addedAdult = "${adultCount.text} Adults"
                } else{
                    addedAdult = "${adultCount.text} Adult"
                }
                personsBooked.add(addedAdult)
            }
        }



        /**Teens-Bottom Sheet Implementation for clicks and selection*/
        addTeenButton.setOnClickListener{
            if(personsBooked.contains(addedTeen)){
                personsBooked.remove(addedTeen)
                viewModel.addToTeensCount()
                if(teensCount.text.toString().toInt()>1){
                    addedTeen = "${teensCount.text} Teens"
                } else{
                    addedTeen = "${teensCount.text} Teen"
                }
                personsBooked.add(addedTeen)
            }
            else{
                viewModel.addToTeensCount()
                if(teensCount.text.toString().toInt()>1){
                    addedTeen = "${teensCount.text} Teens"
                } else{
                    addedTeen = "${teensCount.text} Teen"
                }
                personsBooked.add(addedTeen)
            }
        }


        subtractTeen.setOnClickListener {
            if (teensCount.text.toString().toInt()==0){
                Toast.makeText(requireContext(), "Value must not be less than zero", Toast.LENGTH_SHORT).show()
            }

            else if (personsBooked.contains(addedTeen)){
                personsBooked.remove(addedTeen)
                viewModel.subtractFromTeensCount()
                if(teensCount.text.toString().toInt()>1){
                    addedTeen = "${teensCount.text} Teens"
                } else{
                    addedTeen = "${teensCount.text} Teen"
                }
                personsBooked.add(addedTeen)
            }
        }


        /**Children-Bottom Sheet Implementation for clicks and selection*/
        addChildrenButton.setOnClickListener{
            if(personsBooked.contains(addedChildren)){
                personsBooked.remove(addedChildren)
                viewModel.addToChildrenCount()
                if(childrenCount.text.toString().toInt()>1){
                    addedChildren = "${childrenCount.text} Children"
                } else{
                    addedChildren = "${childrenCount.text} Child"
                }
                personsBooked.add(addedChildren)
            }
            else{
                viewModel.addToChildrenCount()
                if(childrenCount.text.toString().toInt()>1){
                    addedChildren = "${childrenCount.text} Children"
                } else{
                    addedChildren = "${childrenCount.text} Child"
                }
                personsBooked.add(addedChildren)
            }
        }


        subtractChildren.setOnClickListener {
            if (childrenCount.text.toString().toInt()==0){
                Toast.makeText(requireContext(), "Value must not be less than zero", Toast.LENGTH_SHORT).show()
            }

            else if (personsBooked.contains(addedChildren)){
                personsBooked.remove(addedChildren)
                viewModel.subtractFromChildrenCount()
                if(childrenCount.text.toString().toInt()>1){
                    addedChildren = "${childrenCount.text} Children"
                } else{
                    addedChildren = "${childrenCount.text} Child"
                }
                personsBooked.add(addedChildren)
            }
        }



        /**Infant-Bottom Sheet Implementation for clicks and selection*/
        addInfantButton.setOnClickListener {
            if(personsBooked.contains(addedInfant)){
                personsBooked.remove(addedInfant)
                viewModel.addToInfantCount()
                if(infantCount.text.toString().toInt()>1){
                    addedInfant = "${infantCount.text} Infants"
                } else{
                    addedInfant = "${infantCount.text} Infant"
                }
                personsBooked.add(addedInfant)
            }
            else{
                viewModel.addToInfantCount()
                if(infantCount.text.toString().toInt()>1){
                    addedInfant = "${infantCount.text} Infants"
                } else{
                    addedInfant = "${infantCount.text} Infant"
                }
                personsBooked.add(addedInfant)
            }
        }


        subtractInfant.setOnClickListener{
            if (infantCount.text.toString().toInt()==0) {
                Toast.makeText(requireContext(), "Value must not be less than zero", Toast.LENGTH_SHORT).show()
            }

            else if (personsBooked.contains(addedInfant)) {
                personsBooked.remove(addedInfant)
                viewModel.subtractFromInfantCount()
                if(infantCount.text.toString().toInt()>1){
                    addedInfant = "${infantCount.text} Infants"
                } else{
                    addedInfant = "${infantCount.text} Infant"
                }
                personsBooked.add(addedInfant)
            }
        }


        doneText.setOnClickListener {
            listener.OnclickOfDoneTextView(personsBooked.joinToString(","))
                dismiss()
        }

        cancelText.setOnClickListener {
            dismiss()
        }
    }


    private fun observeAdultCount() {
        viewModel.numAdults.observe(viewLifecycleOwner,{
            adultCount.text = it.toString()
        })
    }

    private fun observerTeensCount () {
        viewModel.numTeens.observe(viewLifecycleOwner,{
            teensCount.text = it.toString()
        })
    }

    private fun observeChildrenCount () {
        viewModel.numChildren.observe(viewLifecycleOwner,{
            childrenCount.text = it.toString()
        })
    }

    private fun observeInfantCount() {
        viewModel.numInfant.observe(viewLifecycleOwner,{
            infantCount.text = it.toString()
        })
    }
}
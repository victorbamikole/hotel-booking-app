package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BottomSheetForRooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BootomSheetInterface.AgeBracketListenerInterface
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.BootomSheetInterface.RoomTypeListenerInterface
import com.example.hbapplicationgroupb.validation.convertArrayToString
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetForRooms(private val listener: RoomTypeListenerInterface) : BottomSheetDialogFragment() {
    lateinit var cancelTextBtn : TextView
    lateinit var doneTextBtn : TextView

    lateinit var RoomNo1Text : TextView
    lateinit var RoomNo2Text : TextView
    lateinit var RoomNo22Text : TextView
    lateinit var RoomNo24Text : TextView
    lateinit var RoomNo26Text : TextView

    lateinit var roomNo1RadBtn : CheckBox
    lateinit var roomNo2RadBtn : CheckBox
    lateinit var roomNo22RadBtn : CheckBox
    lateinit var roomNo24RadBtn : CheckBox
    lateinit var roomNo26RadBtn : CheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.room_number_bottom_sheet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancelTextBtn = view.findViewById(R.id.fragment_bookingDetails_CancelBtn_textView)
        doneTextBtn = view.findViewById(R.id.fragment_BookingDetails_doneBtn_textView)

        RoomNo1Text = view.findViewById(R.id.fragment_bookingDetails_Room1Text_textView)
        RoomNo2Text = view.findViewById(R.id.fragment_bookingDetails_Room2Text_textView)
        RoomNo22Text = view.findViewById(R.id.fragment_bookingDetails_Room22Text_textView)
        RoomNo24Text = view.findViewById(R.id.fragment_bookingDetails_Room24Text_textView)
        RoomNo26Text = view.findViewById(R.id.fragment_bookingDetails_Room26Text_textView)


        roomNo1RadBtn = view.findViewById(R.id.fragment_bookingDetails_Room1_RadioButton)
        roomNo2RadBtn = view.findViewById(R.id.fragment_bookingDetails_Room2_RadioButton)
        roomNo22RadBtn = view.findViewById(R.id.fragment_bookingDetails_Room22_RadioButton)
        roomNo24RadBtn = view.findViewById(R.id.fragment_bookingDetails_Room24_RadioButton)
        roomNo26RadBtn = view.findViewById(R.id.fragment_bookingDetails_Room26_RadioButton)


        var roomsBooked : ArrayList<String> = ArrayList(0)
        var room1Booked = " Room No.1"
        var room2Booked = " Room No.2"
        var room22Booked = " Room No.22"
        var room24Booked = " Room No.24"
        var room26Booked = " Room No.26"

        cancelTextBtn.setOnClickListener {
            dismiss()
        }


        doneTextBtn.setOnClickListener {
            if (roomNo1RadBtn.isChecked){
                roomsBooked.add(room1Booked)
            }
            else{
                roomsBooked.remove(room1Booked)
            }

            if (roomNo2RadBtn.isChecked){
                roomsBooked.add(room2Booked)
            }
            else{
                roomsBooked.remove(room2Booked)
            }

            if (roomNo22RadBtn.isChecked){
                roomsBooked.add(room22Booked)
            }
            else{
                roomsBooked.remove(room22Booked)
            }


            if (roomNo24RadBtn.isChecked){
                roomsBooked.add(room24Booked)
            }
            else{
                roomsBooked.remove(room24Booked)
            }


            if (roomNo26RadBtn.isChecked){
                roomsBooked.add(room26Booked)
            }
            else{
                roomsBooked.remove(room26Booked)
            }
            listener.OnclickOfDoneTextViewRoomTypes(roomsBooked.joinToString(","))
            dismiss()
        }
    }
}
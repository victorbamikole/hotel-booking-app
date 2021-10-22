package com.example.hbapplicationgroupb.validation

import android.widget.RadioButton

object BookingDetailsValidation {


    fun validateName(lastName: String):Boolean {
        if (lastName.isEmpty()) {
            return false
        }
        return true
    }


    fun validatePhoneNumber(phoneNumber: String):Boolean {
        return (!phoneNumber.isEmpty() && !phoneNumber.matches("^[a-zA-Z]*$".toRegex())
                && phoneNumber.matches("^[0-9]*$".toRegex())
                && !"(234|0)[0-9]{11}".toRegex().containsMatchIn(phoneNumber))
    }



    fun validateStartDate(startDate: String):Boolean {
        if (startDate.isEmpty()) {
            return false
        }
        return true
    }


    fun validateEndDate(endDate: String):Boolean {
        if (endDate.isEmpty()) {
            return false
        }
        return true
    }


    fun validateAgeBracket(people: String):Boolean {
        if (people.isEmpty()) {
            return false
        }
        return true
    }


    fun validateRoomType(rooms: String):Boolean {
        if (rooms.isEmpty()) {
            return false
        }
        return true
    }





}
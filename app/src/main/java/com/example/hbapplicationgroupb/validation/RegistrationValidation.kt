package com.example.hbapplicationgroupb.validation

import android.widget.RadioButton

object RegistrationValidation {
    fun validateUserFirstName(firstName: String):Boolean {
        return (!firstName.isEmpty() && firstName.matches("^[a-zA-Z]*$".toRegex())
                && !firstName.matches("^[0-9]*$".toRegex()))
    }

    fun validateUserLastName(lastName: String):Boolean {
        return (!lastName.isEmpty() && lastName.matches("^[a-zA-Z]*$".toRegex())
                && !lastName.matches("^[0-9]*$".toRegex()))
    }


    fun validateUserPhoneNumber(phoneNumber: String):Boolean {
        return (!phoneNumber.isEmpty() && !phoneNumber.matches("^[a-zA-Z]*$".toRegex())
                && phoneNumber.matches("^[0-9]*$".toRegex())
                && !"(234|0)[0-9]{11}".toRegex().containsMatchIn(phoneNumber))
    }



    fun validateUserGender(gender: String):Boolean {
        if (gender.isEmpty()) {
            return false
        }
        return true
    }


    fun validateEmail(email: String) : Boolean {
        return (!email.isEmpty() && email.matches("^[a-zA-Z0-9]+\\.?[a-zA-Z0-9]+@[a-z]+\\.[a-z]+".toRegex()))
    }


    fun validateRadioButtonIsChecked(radioButton: RadioButton) : Boolean {
        if (!radioButton.isChecked) {
            return false
        }
        return true
    }
}
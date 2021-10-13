package com.example.hbapplicationgroupb.validation

import android.widget.RadioButton

object RegistrationValidation {
    fun validateUserName(userName: String):Boolean {
        if (userName.isEmpty()) {
            return false
        }
        return true
    }

    fun validateEmail(email: String) : Boolean {
        if (email.isEmpty()) {
            return false
        }
        return true
    }


    fun validatePassword(password: String) : Boolean {
        val passwordPattern = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{4,}$".toRegex()
        if (password.matches(passwordPattern)||password.isNotEmpty()){
            return true
        }
        return false
    }


    fun validateRadioButtonIsChecked(radioButton: RadioButton) : Boolean {
        if (!radioButton.isChecked) {
            return false
        }
        return true
    }
}
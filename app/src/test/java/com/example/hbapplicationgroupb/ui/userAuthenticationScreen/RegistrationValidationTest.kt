package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import com.example.hbapplicationgroupb.validation.RegistrationValidation
import org.junit.Assert.*
import org.junit.Test

class RegistrationValidationTest{


    @Test
    fun validateUserName() {
        var result = RegistrationValidation.validateUserFirstName ("we")
        assertEquals(true,result)
    }

    @Test
    fun validateEmail() {
        var result = RegistrationValidation.validateEmail("samuel.onum1@gmail.com")
        assertEquals(true,result)
    }


    @Test
    fun validatePassword() {
        var result = RegistrationValidation.validatePassword("Password1$")
        assertEquals(true, result)
    }

//    @Test
//    fun validateRadioButtonIsChecked() {
//        var result = RegistrationValidation.validateRadioButtonIsChecked()
//        assertEquals(false, result)
//    }
}
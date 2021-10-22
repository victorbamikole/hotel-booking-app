package com.example.hbapplicationgroupb.validation

import com.example.hbapplicationgroupb.validation.RegistrationValidation
import org.junit.Assert.*
import org.junit.Test

class RegistrationValidationTest{


    @Test
    fun validateUserName() {
        var result = RegistrationValidation.validateUserFirstName ("wehvcvvcvb")
        assertEquals(true,result)
    }

    @Test
    fun validateEmail() {
        var result = RegistrationValidation.validateEmail("samuel.onum1@gmail.com")
        assertEquals(true,result)
    }

//    @Test
//    fun validateRadioButtonIsChecked() {
//        var result = RegistrationValidation.validateRadioButtonIsChecked()
//        assertEquals(false, result)
//    }
}
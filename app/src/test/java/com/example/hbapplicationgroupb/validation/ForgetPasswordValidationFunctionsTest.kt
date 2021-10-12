package com.example.hbapplicationgroupb.validation

import org.junit.Assert.*

import org.junit.Test

class ForgetPasswordValidationFunctionsTest {

    @Test
    fun checkIfFieldNotEmpty_returnTrueIfFieldIsNotEmpty_returnTrue() {
        assertTrue(ResetPasswordValidationFunctions.checkIfFieldNotEmpty("m"))
    }

    @Test
    fun checkIfFieldNotEmpty_returnTrueIfFieldIsNotEmpty_returnFalse() {
        assertFalse(ResetPasswordValidationFunctions.checkIfFieldNotEmpty(""))
    }

    //validate email field

    @Test
    fun checkIfValid_returnTrueIfEmailIsValidCase1_returnTrue(){
        assertTrue(ForgetPasswordValidationFunctions.checkIfEmailIsValid("oyesinaJohnson@yahoo.com"))
    }

    @Test
    fun checkIfValid_returnTrueIfEmailIsValidCase2_returnTrue(){
        assertTrue(ForgetPasswordValidationFunctions.checkIfEmailIsValid("oyesinaJohnson@yahoo.dev"))
    }
    @Test
    fun checkIfValid_returnTrueIfEmailIsValidCase1_returnFalse(){
        assertFalse(ForgetPasswordValidationFunctions.checkIfEmailIsValid("oyesinajohnson@gmail."))
    }
    @Test
    fun checkIfValid_returnTrueIfEmailIsValidCase2_returnFalse(){
        assertFalse(ForgetPasswordValidationFunctions.checkIfEmailIsValid("oyesina"))
    }
}
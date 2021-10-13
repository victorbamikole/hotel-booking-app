package com.example.hbapplicationgroupb.validation


import org.junit.Assert.*

import org.junit.Test

class ResetPasswordValidationFunctionsTest {

    @Test
    fun checkIfFieldNotEmpty_returnTrueIfFieldIsNotEmpty_returnTrue() {
        assertTrue(ResetPasswordValidationFunctions.checkIfFieldNotEmpty("m"))
    }

    @Test
    fun checkIfFieldNotEmpty_returnTrueIfFieldIsNotEmpty_returnFalse() {
        assertFalse(ResetPasswordValidationFunctions.checkIfFieldNotEmpty(""))
    }

    @Test
    fun checkIfPasswordMatches_returnTrueIfPasswordMatchesCase1_returnTrue() {
        assertTrue(ResetPasswordValidationFunctions.checkIfPasswordMatches("m","m"))
    }
    @Test
    fun checkIfPasswordMatches_returnTrueIfPasswordMatchesCase2_returnTrue() {
        assertTrue(ResetPasswordValidationFunctions.checkIfPasswordMatches("",""))
    }
    @Test
    fun checkIfPasswordMatches_returnTrueIfPasswordMatchesCase1_returnFalse() {
        assertFalse(ResetPasswordValidationFunctions.checkIfPasswordMatches("mM","Mm"))
    }

    @Test
    fun checkIfPassWordIsValid_returnZeroIfPasswordIsValidCase1_returnZero() {
        assertEquals(0,ResetPasswordValidationFunctions.checkIfPassWordIsValid("Oyesina@123"))
    }
    @Test
    fun checkIfPassWordIsValid_returnOneIfPasswordIsLessThanSIx_returnOne() {
        assertEquals(1,ResetPasswordValidationFunctions.checkIfPassWordIsValid("Oy123"))
    }
    @Test
    fun checkIfPassWordIsValid_returnTwoIfPasswordDoesNotContainNumber_returnTwo() {
        assertEquals(2,ResetPasswordValidationFunctions.checkIfPassWordIsValid("OyesinaJohnson"))
    }
}
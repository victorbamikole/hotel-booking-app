package com.example.hbapplicationgroupb.validation

import org.junit.Assert.*
import org.junit.Test

class BookingDetailsValidationTest {


    @Test
    fun checkIf_Name_Is_Valid() {
        assertTrue( BookingDetailsValidation.validateName("Sam"))
    }

    @Test
    fun checkIf_Phone_Number_Is_Valid() {
        assertTrue( BookingDetailsValidation.validatePhoneNumber("08091185347"))
    }


    @Test
    fun checkIf_Start_Date_Is_Empty() {
        assertTrue( BookingDetailsValidation.validateStartDate ("Wednesday, 13, October, 2021"))
    }


    @Test
    fun checkIf_End_Date_Is_Empty() {
        assertTrue( BookingDetailsValidation.validateEndDate("Wednesday, 26, October, 2021"))
    }

    @Test
    fun checkIf_People_Field_Is_Empty() {
        assertTrue( BookingDetailsValidation.validateAgeBracket("1 Adult"))
    }


    @Test
    fun checkIf_Room_Field_Is_Empty() {
        assertTrue( BookingDetailsValidation.validateRoomType ("Room 1"))
    }

}
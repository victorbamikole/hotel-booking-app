package com.example.hbapplicationgroupb.validation

import org.junit.Assert.*

import org.junit.Test

class UpdateUserValidationTest {

    @Test
    fun validateUpdatedUserFirstName() {

        val result = UpdateUserValidation.validateUpdatedUserFirstName ("Samuel")
        assertEquals(true,result)
    }

    @Test
    fun validateUpdatedUserLastName() {
        val result = UpdateUserValidation.validateUpdatedUserLastName ("Ungbede")
        assertEquals(true,result)
    }

    @Test
    fun validateUpdatedUserPhoneNumber() {
        val result = UpdateUserValidation.validateUpdatedUserPhoneNumber ("08091185347")
        assertEquals(true,result)
    }

    @Test
    fun validateUpdatedUserAddress() {
        val result = UpdateUserValidation.validateUpdatedUserAddress ("EdoTecPack")
        assertEquals(true,result)
    }

    @Test
    fun validateUpdatedUserState() {
        val result = UpdateUserValidation.validateUpdatedUserState ("Edo State")
        assertEquals(true,result)
    }
}
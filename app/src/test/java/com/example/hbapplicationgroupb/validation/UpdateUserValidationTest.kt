package com.example.hbapplicationgroupb.validation

import org.junit.Assert.*

import org.junit.Test

class UpdateUserValidationTest {

    @Test
    fun validateUpdatedUserFirstName() {

        var result = UpdateUserValidation.validateUpdatedUserFirstName ("Samuel")
        assertEquals(true,result)
    }

    @Test
    fun validateUpdatedUserLastName() {
        var result = UpdateUserValidation.validateUpdatedUserLastName ("Ungbede")
        assertEquals(true,result)
    }

    @Test
    fun validateUpdatedUserPhoneNumber() {
        var result = UpdateUserValidation.validateUpdatedUserPhoneNumber ("08091185347")
        assertEquals(true,result)
    }

    @Test
    fun validateUpdatedUserAddress() {
        var result = UpdateUserValidation.validateUpdatedUserAddress ("EdoTecPack")
        assertEquals(true,result)
    }

    @Test
    fun validateUpdatedUserState() {
        var result = UpdateUserValidation.validateUpdatedUserState ("Edo State")
        assertEquals(true,result)
    }
}
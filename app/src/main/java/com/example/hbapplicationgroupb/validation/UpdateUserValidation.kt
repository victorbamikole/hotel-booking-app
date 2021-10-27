package com.example.hbapplicationgroupb.validation

object UpdateUserValidation {

    fun validateUpdatedUserFirstName (firstName: String):Boolean {
        return (!firstName.isEmpty() && firstName.matches("^[a-zA-Z]*$".toRegex())
                && !firstName.matches("^[0-9]*$".toRegex()))
    }

    fun validateUpdatedUserLastName (lastName: String):Boolean {
        return (!lastName.isEmpty() && lastName.matches("^[a-zA-Z]*$".toRegex())
                && !lastName.matches("^[0-9]*$".toRegex()))
    }


    fun validateUpdatedUserPhoneNumber (phoneNumber: String):Boolean {
        return (!phoneNumber.isEmpty() && !phoneNumber.matches("^[a-zA-Z]*$".toRegex())
                && phoneNumber.matches("^[0-9]*$".toRegex())
                && !"(234|0)[0-9]{11}".toRegex().containsMatchIn(phoneNumber))
    }


    fun validateUpdatedUserAddress (address: String):Boolean {
        return (!address.isEmpty())
    }


    fun validateUpdatedUserState (state: String):Boolean {
        return (!state.isEmpty())
    }

}
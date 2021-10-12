package com.example.hbapplicationgroupb.validation

object ResetPasswordValidationFunctions {
    fun checkIfFieldNotEmpty(string: String):Boolean{
        if (string.isNotEmpty()||string.isNotBlank()){
            return true
        }
        return false
    }
    fun checkIfPasswordMatches(stringOne: String, stringTwo: String):Boolean{
        if (stringOne == stringTwo){
            return true
        }
        return false
    }
    fun checkIfPassWordIsValid(string: String):Int{
        if (string.length < 6){
            // one means throw error password less than 6
            return 1
        }
        if (string.length >= 6 && !string.contains(Regex("[0-9] [a-zA-Z] [@#$%^&+=]"))){
            //two means throw error that password does not contain number
            return 2
        }
        return 0
    }
}

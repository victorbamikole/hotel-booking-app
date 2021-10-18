package com.example.hbapplicationgroupb.validation

import java.util.regex.Matcher
import java.util.regex.Pattern

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

     fun isValidPassword(password: String): Boolean {
        val pattern: Pattern
        val specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_"
        val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$specialCharacters])(?=\\S+$).{8,20}$"
        pattern = Pattern.compile(PASSWORD_REGEX)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }
    fun checkIfPassWordIsValid(string: String):Boolean {
        if (string.length >= 8 ) {
            return true
        }
        return false
    }

}

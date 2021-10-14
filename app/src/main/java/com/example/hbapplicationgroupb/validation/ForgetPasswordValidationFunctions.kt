package com.example.hbapplicationgroupb.validation

object ForgetPasswordValidationFunctions {
    //Forget password field validation
    fun checkIfFieldNotEmpty(string: String):Boolean{
        if (string.isNotEmpty()||string.isNotBlank()){
            return true
        }
        return false
    }
    fun checkIfEmailIsValid(string: String):Boolean {
        val chr = string.split(".")
        val validEnd = chr[chr.size - 1]
        if (string.any { it in "@." } && validEnd.contains(Regex("[a-z]"))) return true
        return false
    }

}
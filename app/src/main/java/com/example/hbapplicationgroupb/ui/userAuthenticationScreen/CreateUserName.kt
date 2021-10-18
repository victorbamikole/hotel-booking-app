package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

object CreateUserName {

    fun createUserName (firstName: String, lastName: String) : String{
        val userName =  "${firstName.trim()}${lastName.trim()}"
        return userName
    }
}
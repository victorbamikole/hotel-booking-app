package com.example.hbapplicationgroupb.ui.userAuthenticationScreen



    fun createUserName (firstName: String, lastName: String) : String{
        val rnd = (0..100).random()
        val rnd2 = (0..100).random()
        val userName =  "${firstName.trim()}${lastName.trim()}$rnd$rnd2"
        return userName
    }

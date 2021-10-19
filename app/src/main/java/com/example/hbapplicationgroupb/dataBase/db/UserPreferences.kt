package com.example.hbapplicationgroupb.dataBase.db

import android.content.Context
import android.content.SharedPreferences
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.util.constants.PREFERENCE_NAME
import com.example.hbapplicationgroupb.util.constants.DEFAULT_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserPreferences @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

    val editor: SharedPreferences.Editor = sharedPreferences.edit()

    //Set getter for the sharedPreference
    fun returnSharedPref() = sharedPreferences

    //Save user session whenever user is logged in
    fun saveSession(user : UserDataResponseItem){

    }

    fun getSessionUser() : String? {
        return sharedPreferences.getString("userToken", DEFAULT_TOKEN)
    }
}
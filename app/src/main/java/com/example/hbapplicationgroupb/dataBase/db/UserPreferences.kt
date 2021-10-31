package com.example.hbapplicationgroupb.dataBase.db

import android.content.Context
import android.content.SharedPreferences
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.util.constants.*
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
    fun saveSession(token : String,userId:String,refreshToken:String){
        editor.putString(SHARED_PREF_KEY_TOKEN, token).commit()
        editor.putString(SHARED_PREF_KEY_REFRESH_TOKEN, refreshToken).commit()
        editor.putString(SHARED_PREF_KEY_USER_ID, userId).commit()
    }

    fun getSavedToken() : String{
        val userId = sharedPreferences.getString(SHARED_PREF_KEY_USER_ID, DEFAULT_TOKEN)
        val refreshToken = sharedPreferences.getString(SHARED_PREF_KEY_REFRESH_TOKEN, DEFAULT_TOKEN)
        return "$userId,$refreshToken"
    }

    fun clearUserSession(){
        editor.putString(SHARED_PREF_KEY_TOKEN, DEFAULT_TOKEN).commit()
        editor.putString(SHARED_PREF_KEY_REFRESH_TOKEN, DEFAULT_TOKEN).commit()
        editor.putString(SHARED_PREF_KEY_USER_ID, DEFAULT_TOKEN).commit()
    }
}
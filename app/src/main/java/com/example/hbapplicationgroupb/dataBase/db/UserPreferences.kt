package com.example.hbapplicationgroupb.dataBase.db

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.viewModels
import com.example.hbapplicationgroupb.model.userData.UserDataResponseItem
import com.example.hbapplicationgroupb.util.constants.*
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
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

    fun getUserId() : String{
        val userId = sharedPreferences.getString(SHARED_PREF_KEY_USER_ID, DEFAULT_TOKEN)
        return userId!!
    }

    fun getUserRefreshToken():String{
        val refreshToken = sharedPreferences.getString(SHARED_PREF_KEY_REFRESH_TOKEN, DEFAULT_TOKEN)

        return refreshToken!!
    }
    fun getUserToken():String{
        val userToken = sharedPreferences.getString(SHARED_PREF_KEY_TOKEN, DEFAULT_TOKEN)
        return userToken!!
    }

    fun clearUserSession(){
        editor.putString(SHARED_PREF_KEY_TOKEN, DEFAULT_TOKEN).commit()
        editor.putString(SHARED_PREF_KEY_REFRESH_TOKEN, DEFAULT_TOKEN).commit()
        editor.putString(SHARED_PREF_KEY_USER_ID, DEFAULT_TOKEN).commit()
    }
}
package com.example.hbapplicationgroupb.util.constants

import android.view.View
import com.google.android.material.snackbar.Snackbar


const val BASE_URL = "https://hoteldotnet.herokuapp.com/"

    const val TOP_HOTEL_TABLE = "top_hotel_table"
    const val PREFERENCE_NAME = "hotel_preference"

const val DEFAULT_TOKEN = "1"
const val SHARED_PREF_KEY_TOKEN = "userToken"
const val SHARED_PREF_KEY_REFRESH_TOKEN = "refreshToken"
const val SHARED_PREF_KEY_USER_ID = "userId"
const val CAMERA_PERMISSION_CODE = 1
const val CAMERA_REQUEST_CODE = 2
const val GALLERY_REQUEST_CODE = 3
const val IMAGE_DIRECTORY = "HotelProfileImage"


fun View.snackbar(message:String){
    Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_LONG
    ).also {snackbar ->
        snackbar.setAction("ok"){
            snackbar.dismiss()
        }
    }.show()
}

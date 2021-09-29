package com.example.hbapplicationgroupb.model

import androidx.annotation.DrawableRes

data class RoomImageAndDetailForViewPager (
    @DrawableRes val image:Int,
    val placeTitle:String,
    val placePrice:String
        )
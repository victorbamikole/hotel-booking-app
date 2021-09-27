package com.example.hbapplicationgroupb.model

import androidx.annotation.DrawableRes

data class UserReview(
    val name:String,
    val ratings:Int,
    @DrawableRes val image:Int,
    val review:String
)

package com.example.hbapplicationgroupb.model.userData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserDataResponseItem(
    val firstName: String,
    val lastName: String,
    val email: String,
    val userName: String? = null,
    val password: String,
    val phoneNumber: String,
    val gender: String,
    val age: Int? = null
) : Parcelable

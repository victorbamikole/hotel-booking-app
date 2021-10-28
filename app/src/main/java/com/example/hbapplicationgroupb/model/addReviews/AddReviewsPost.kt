package com.example.hbapplicationgroupb.model.addReviews

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddReviewsPost(
    val comment: String,
    val hotelId: String
) : Parcelable

package com.example.hbapplicationgroupb.model.topdealsdata

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ListOfTopDealsResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<ListOfTopDealsItem>,
    val message: String,
    val errors: String? = null

)
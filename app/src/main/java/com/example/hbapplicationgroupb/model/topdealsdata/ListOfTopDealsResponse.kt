package com.example.hbapplicationgroupb.model.topdealsdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Top Deals")
data class ListOfTopDealsResponse(
    @PrimaryKey
    val id: Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<ListOfTopDealsItem>,
    val message: String,
    val errors: String? = null

)
package com.example.hbapplicationgroupb.model.topdealsdata

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Top_Deals_Table")
data class ListOfTopDealsItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val email: String,
    val address: String,
    val city: String,
    val state: String,
    val price: String
)

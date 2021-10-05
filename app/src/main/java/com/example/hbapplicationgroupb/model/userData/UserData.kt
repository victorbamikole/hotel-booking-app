package com.example.hbapplicationgroupb.model.userData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User Data")
data class UserData(
    @PrimaryKey
    val id:Int,
    val statusCode: String,
    val success: Boolean,
    val data: List<UserDataItems>,
    val message: String,
    val errors: String? = null
)

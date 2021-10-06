package com.example.hbapplicationgroupb.dataBase.dao

import androidx.room.Dao
import androidx.room.TypeConverter
import java.util.*


@Dao
class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }
}
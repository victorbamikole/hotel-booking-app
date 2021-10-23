package com.example.hbapplicationgroupb.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.hbapplicationgroupb.model.allhotel.RoomType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//@ProvidedTypeConverter
class HotelTypeConverter {

    @TypeConverter
    fun fromString(value :String) :List<String>{

        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(value,listType)

    }

    @TypeConverter
    fun fromList(list :List<String>) :String{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromRoomType(list :List<RoomType>) : String{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToRoomType(value :String?) :List<RoomType>{
        val roomType = object : TypeToken<List<RoomType>>(){}.type
        return Gson().fromJson(value,roomType)
    }
}
package com.advance.emotionscanapp.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return if (value == null) {
            emptyList()
        } else {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(value, type)
        }
    }

    @TypeConverter
    fun toString(list: List<String>?): String {
        return gson.toJson(list ?: "")
    }

}
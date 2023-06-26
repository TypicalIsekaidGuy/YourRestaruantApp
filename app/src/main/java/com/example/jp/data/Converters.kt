package com.example.jp.data

import androidx.room.TypeConverter
class Converters {
    @TypeConverter
    fun fromBoolToInt(value: Int): Boolean {
        return (value==1)
    }

    @TypeConverter
    fun toIntFromBool(value: Boolean): Int {
        return if (value) 1 else 0
    }
}

package com.example.jp.data

import androidx.room.TypeConverter
import com.example.jp.data.products.Products
import com.google.gson.Gson

class Converters {
    private val gson = Gson()
    @TypeConverter
    fun fromBoolToInt(value: Int): Boolean {
        return (value==1)
    }


//products is not type that is in sql so i need to convert it in json, kinda makes sense
    @TypeConverter
    fun fromJson(json: String): Products {
        return gson.fromJson(json, Products::class.java)
    }

    @TypeConverter
    fun toJson(products: Products): String {
        return gson.toJson(products)
    }
}

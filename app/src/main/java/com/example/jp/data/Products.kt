package com.example.jp.data

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class Products(
    @PrimaryKey(autoGenerate = true)
    val title: String,
    val type: String,
    val description: String,
    @DrawableRes val iconId: Int,
    val isNew: Boolean,
    val price: Int
)

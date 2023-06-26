package com.example.jp.data.products

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class Products(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tittle: String,
    val type: String,
    val description: String,
    val icon: ByteArray,
    val price: Int,
    val isNew: Boolean
)

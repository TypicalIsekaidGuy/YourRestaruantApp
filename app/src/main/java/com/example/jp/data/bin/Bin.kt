package com.example.jp.data.products

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Bin")
data class Bin(
    @PrimaryKey
    val id: Int,
    val product: Products,
    val quantity: Int
)

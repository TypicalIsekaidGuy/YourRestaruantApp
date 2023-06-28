package com.example.jp.data.onSale

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OnSale")
data class OnSale(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tittle: String,
    val description: String,
    val image: ByteArray
)

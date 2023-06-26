package com.example.jp.data.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tittle: String,
    val description: String,
    val image: ByteArray
)

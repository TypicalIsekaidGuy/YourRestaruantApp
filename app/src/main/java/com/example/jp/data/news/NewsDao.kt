package com.example.jp.data.news

import androidx.room.Dao
import androidx.room.Query

@Dao
interface NewsDao {
    @Query("SELECT * FROM News ORDER BY id ASC")
    fun getAllProducts(): List<News>
}
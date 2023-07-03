package com.example.jp.data.news

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert
    fun insertNews(news: News)
    @Query("SELECT * FROM News ORDER BY id ASC")
    fun getAllProducts(): List<News>
}
package com.example.jp.data.news

import androidx.room.*
import com.example.jp.data.Converters
import com.example.jp.data.news.NewsDao

@Database(
    entities = [News::class],
    version = 8
)
@TypeConverters(Converters::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao
}
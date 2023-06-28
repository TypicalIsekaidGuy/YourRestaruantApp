package com.example.jp.data.news

import android.content.Context
import androidx.room.*
import com.example.jp.data.Converters
import com.example.jp.data.news.NewsDao

@Database(
    entities = [News::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao

    companion object {
        @Volatile
        private var instance: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news.db"
                )
                    .fallbackToDestructiveMigration()
                    .createFromAsset("Data/News.db")
                    .build()
                instance = db
                db
            }
        }
    }
}

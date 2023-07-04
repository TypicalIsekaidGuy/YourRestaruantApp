package com.example.jp.data.news


import android.content.Context
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.room.*
import com.example.jp.data.Converters
import com.example.jp.data.news.NewsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//Databases for now are used only for the one table, maybe i should fix this
@Database(
    entities = [News::class],
    version = 4
)
@TypeConverters(Converters::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao

    companion object {//needed everywhere to get the instance to database however works only 1 time so i should change this
        @Volatile
        var instance: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase {
            if(instance == null){
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news.db"
                )
                    .fallbackToDestructiveMigration()
                    .createFromAsset("Data/News.db")
                    .build()
                instance = db
            }
            return instance as NewsDatabase
        }
    }
}

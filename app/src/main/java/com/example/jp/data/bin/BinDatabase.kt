package com.example.jp.data.bin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jp.data.Converters
import com.example.jp.data.news.News
import com.example.jp.data.news.NewsDao
import com.example.jp.data.products.Bin

@Database(
    entities = [Bin::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class BinDatabase: RoomDatabase() {

    abstract val binDao: BinDao

    companion object {
        @Volatile
        var instance: BinDatabase? = null

        fun getInstance(context: Context): BinDatabase {
            if(instance == null){
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    BinDatabase::class.java,
                    "bin.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                instance = db
            }
            return instance as BinDatabase
        }
    }
}
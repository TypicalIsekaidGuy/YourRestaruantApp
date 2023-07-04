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
//Databases for now are used only for the one table, maybe i should fix this
@Database(
    entities = [Bin::class],
    version = 4
)
@TypeConverters(Converters::class)
abstract class BinDatabase: RoomDatabase() {

    abstract val binDao: BinDao

    companion object {//needed everywhere to get the instance to database however works only 1 time so i should change this
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
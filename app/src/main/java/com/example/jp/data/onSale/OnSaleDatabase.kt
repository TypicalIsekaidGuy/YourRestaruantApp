package com.example.jp.data.onSale

import android.content.Context
import androidx.room.*
import com.example.jp.data.Converters
import com.example.jp.data.news.NewsDao
import com.example.jp.data.news.NewsDatabase

@Database(
    entities = [OnSale::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class OnSaleDatabase: RoomDatabase() {

    abstract val onSaleDao: OnSaleDao

    companion object {
        @Volatile
        var instance: OnSaleDatabase? = null

        fun getInstance(context: Context): OnSaleDatabase {
            if(instance == null){
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    OnSaleDatabase::class.java,
                    "OnSale.db"
                )
                    .fallbackToDestructiveMigration()
                    .createFromAsset("Data/OnSale.db")
                    .build()
                instance = db
            }
            return instance as OnSaleDatabase
        }
    }
}
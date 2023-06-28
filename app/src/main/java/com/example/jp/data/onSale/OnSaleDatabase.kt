package com.example.jp.data.onSale

import androidx.room.*
import com.example.jp.data.Converters
import com.example.jp.data.news.NewsDao

@Database(
    entities = [OnSale::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class OnSaleDatabase: RoomDatabase() {

    abstract val onSaleDao: OnSaleDao
}
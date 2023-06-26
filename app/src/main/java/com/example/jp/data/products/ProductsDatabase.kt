package com.example.jp.data.products

import androidx.room.*
import com.example.jp.data.Converters
import com.example.jp.data.news.NewsDao

@Database(
    entities = [Products::class],
    version = 8
)
@TypeConverters(Converters::class)
abstract class ProductsDatabase: RoomDatabase() {

    abstract val productsDao: ProductsDao
}
package com.example.jp.data

import androidx.room.*

@Database(
    entities = [Products::class],
    version = 8
)
@TypeConverters(Converters::class)
abstract class ProductsDatabase: RoomDatabase() {

    abstract val dao: ProductsDao
}
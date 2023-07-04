package com.example.jp.data.products

import android.content.Context
import androidx.room.*
import com.example.jp.data.Converters
import com.example.jp.data.news.NewsDao
import com.example.jp.data.onSale.OnSaleDatabase
//Databases for now are used only for the one table, maybe i should fix this
@Database(
    entities = [Products::class],
    version = 8
)
@TypeConverters(Converters::class)
abstract class ProductsDatabase: RoomDatabase() {

    abstract val productsDao: ProductsDao

    companion object {//needed everywhere to get the instance to database however works only 1 time so i should change this
        @Volatile
        var instance: ProductsDatabase? = null

        fun getInstance(context: Context): ProductsDatabase {
            if(instance == null){
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "ProductsDatabase.db"
                )
                    .fallbackToDestructiveMigration()
                    .createFromAsset("Data/Products.db")
                    .build()
                instance = db
            }
            return instance as ProductsDatabase
        }
    }
}
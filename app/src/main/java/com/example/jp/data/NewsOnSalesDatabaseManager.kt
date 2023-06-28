package com.example.jp.data

import android.content.Context
import androidx.room.Room
import com.example.jp.data.news.NewsDatabase
import com.example.jp.data.onSale.OnSaleDatabase
import com.example.jp.data.products.ProductsDatabase

// DatabaseManager.kt

class NewsOnSalesDatabaseManager {
    companion object{
    private var newsDatabase: NewsDatabase? = null
    private var onSaleDatabase: OnSaleDatabase? = null
    private var ProductsDatabase: ProductsDatabase? = null

    fun getNewsDatabase(context: Context): NewsDatabase {
        return newsDatabase ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news.db"
            )
                .fallbackToDestructiveMigration()
                .createFromAsset("Data/News.db")
                .build()
            newsDatabase = instance
            instance
        }
    }

    fun getOnSaleDatabase(context: Context): OnSaleDatabase {
        return onSaleDatabase ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                OnSaleDatabase::class.java,
                "onSale.db"
            )
                .fallbackToDestructiveMigration()
                .createFromAsset("Data/OnSale.db")
                .build()
            onSaleDatabase = instance
            instance
        }
    }
    }
/*    fun getProductsDatabase(context: Context): ProductsDatabase {
        return ProductsDatabase ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ProductsDatabase::class.java,
                "onSale.db"
            )
                .fallbackToDestructiveMigration()
                .createFromAsset("Data/ProductsDatabase.db")
                .build()
            ProductsDatabase = instance
            instance
        }
    }*/

}
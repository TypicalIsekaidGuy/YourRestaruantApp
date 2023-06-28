package com.example.jp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.room.Room
import com.example.jp.ProfileScreen
import com.example.jp.data.products.Products
import com.example.jp.data.products.ProductsDatabase
import com.example.jp.data.news.News
import com.example.jp.data.news.NewsDatabase
import com.example.jp.data.onSale.OnSale
import com.example.jp.data.onSale.OnSaleDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            NewsDatabase::class.java,
            "news.db"
        )
            .fallbackToDestructiveMigration()
            .createFromAsset("Data/News.db")
            .build()
    }
    private val db2 by lazy {
        Room.databaseBuilder(
            applicationContext,
            OnSaleDatabase::class.java,
            "onSale.db"
        )
            .fallbackToDestructiveMigration()
            .createFromAsset("Data/OnSale.db")
            .build()
    }
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val newsState = remember { mutableStateOf(emptyList<News>()) }
            val onSaleState = remember { mutableStateOf(emptyList<OnSale>()) }

            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    val news = db.newsDao.getAllProducts()
                    newsState.value = news
                }
            }


            val productsState = remember { mutableStateOf(emptyList<OnSale>()) }

            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    val onSale = db2.onSaleDao.getAllProducts()
                    onSaleState.value = onSale
                }
            }
            ProfileScreen(newsState, onSaleState)
        }
    }
}
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
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val productsState = remember { mutableStateOf(emptyList<News>()) }

            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    val news = db.newsDao.getAllProducts()
                    productsState.value = news
                }
            }
            ProfileScreen(productsState)
        }
    }
}
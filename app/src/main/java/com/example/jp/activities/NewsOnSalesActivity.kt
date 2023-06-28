package com.example.jp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.room.Room
import com.example.jp.NewsOnSaleScreen
import com.example.jp.ProfileScreen
import com.example.jp.data.NewsOnSalesDatabaseManager
import com.example.jp.data.products.Products
import com.example.jp.data.products.ProductsDatabase
import com.example.jp.data.news.News
import com.example.jp.data.news.NewsDatabase
import com.example.jp.data.onSale.OnSale
import com.example.jp.data.onSale.OnSaleDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsOnSalesActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val newsState = remember { mutableStateOf(emptyList<News>()) }
            val onSaleState = remember { mutableStateOf(emptyList<OnSale>()) }

            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    val news = NewsOnSalesDatabaseManager.getNewsDatabase(applicationContext).newsDao.getAllProducts()
                    newsState.value = news
                }
            }

            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    val onSale = NewsOnSalesDatabaseManager.getOnSaleDatabase(applicationContext).onSaleDao.getAllProducts()
                    onSaleState.value = onSale
                }
            }
            NewsOnSaleScreen(newsState, onSaleState, applicationContext, intent.getIntExtra("EXTRA_INDEX", 0), intent.getBooleanExtra("EXTRA_IS_NEWS", true))
        }
    }
}

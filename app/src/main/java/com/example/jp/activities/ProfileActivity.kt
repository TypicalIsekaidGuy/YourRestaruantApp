package com.example.jp.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.runtime.mutableStateOf
import com.example.jp.ui.screen.ProfileScreen
import com.example.jp.data.news.News
import com.example.jp.data.news.NewsDatabase
import com.example.jp.data.onSale.OnSale
import com.example.jp.data.onSale.OnSaleDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : ComponentActivity() {

companion object{
    var news: List<News>? = null
    var OnSales: List<OnSale>? = null
    fun getNews(context: Context): List<News>{
        if(news==null)
            news = NewsDatabase.getInstance(context.applicationContext).newsDao.getAllProducts()
        return news!!
    }
    fun getOnSales(context: Context): List<OnSale>{
        if(OnSales==null)
            OnSales = OnSaleDatabase.getInstance(context.applicationContext).onSaleDao.getAllProducts()
        return OnSales!!
    }
}
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newsState = mutableStateOf(emptyList<News>())
        val onSaleState = mutableStateOf(emptyList<OnSale>())

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            val news = withContext(Dispatchers.IO) {
                getNews(applicationContext)
            }
            val onSale = withContext(Dispatchers.IO) {
                getOnSales(applicationContext)
            }
            onSaleState.value = onSale
            newsState.value = news
        }
        setContent {
            ProfileScreen(newsState.value, onSaleState.value, applicationContext)
        }
    }
}
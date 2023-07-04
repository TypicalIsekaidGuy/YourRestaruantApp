package com.example.jp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import com.example.jp.ui.screen.NewsOnSaleScreen

class NewsOnSalesActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val news = ProfileActivity.news!!
        val onSale = ProfileActivity.OnSales!!

        setContent(){
            NewsOnSaleScreen(news, onSale, applicationContext, intent.getIntExtra("EXTRA_INDEX", 0), intent.getBooleanExtra("EXTRA_IS_NEWS", true))
        }
    }
}

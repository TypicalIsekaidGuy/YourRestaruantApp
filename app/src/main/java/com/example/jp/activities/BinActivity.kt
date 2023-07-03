package com.example.jp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import com.example.jp.BinScreen
import com.example.jp.NewsOnSaleScreen
import com.example.jp.data.bin.BinDatabase
import com.example.jp.data.news.News
import com.example.jp.data.onSale.OnSale
import com.example.jp.data.products.Bin
import com.example.jp.data.products.Products
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BinActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binState = mutableStateOf(emptyList<Bin>())
        val supplementsState = mutableStateOf(emptyList<Products>())

        val scope = CoroutineScope(Dispatchers.Main)

        var total = 0

        scope.launch {
            val bins = withContext(Dispatchers.IO) {
                MenuActivity.getBinDao(applicationContext).getAllProducts()
            }
            binState.value = bins
            for (i in bins)
                total+= i.quantity*i.price

            val supplements = withContext(Dispatchers.IO) {
                MenuActivity.getSupplements(applicationContext)
            }
            supplementsState.value = supplements
        }
        Log.d("SSSS",total.toString())
        setContent{
            BinScreen(MenuActivity.getBinDao(applicationContext), context = applicationContext, db = binState, total = total, supplements = supplementsState.value )
        }
    }
}

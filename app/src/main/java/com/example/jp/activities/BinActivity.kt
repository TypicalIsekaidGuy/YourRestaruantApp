package com.example.jp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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

        val binState: MutableState<MutableList<Bin>> = mutableStateOf(mutableListOf())
        val supplementsState = mutableStateOf(emptyList<Products>())

        val scope = CoroutineScope(Dispatchers.Main)

        var total = 0

        scope.launch {
            val bins = withContext(Dispatchers.IO) {
                MenuActivity.getBinDao(applicationContext).getAllProducts()
            }
            binState.value = bins as MutableList<Bin>
            for (i in bins)
                total+= i.quantity*i.product.price

            val supplements = withContext(Dispatchers.IO) {
                MenuActivity.getSupplements(applicationContext)
            }
            supplementsState.value = supplements
        }
        Log.d("SSSS",total.toString())
        setContent{
            BinScreen(MenuActivity.getBinDao(applicationContext), context = applicationContext, db = binState, supplements = supplementsState.value )
        }
    }
/*    fun startTokenize() {
        val paymentParameters = PaymentParameters(
            amount = Amount(BigDecimal.TEN, Currency.getInstance("RUB")),
            title = "Product name",
            subtitle = "Product description",
            clientApplicationKey = "live_thisKeyIsNotReal", // key for client apps from the YooMoney Merchant Profile
            shopId = "12345", // ID of the store in the YooMoney system
            savePaymentMethod = SavePaymentMethod.OFF, // flag of the disabled option to save payment methods,
            paymentMethodTypes = setOf(PaymentMethodType.YOO_MONEY, PaymentMethodType.BANK_CARD, PaymentMethodType.SBERBANK, PaymentMethodType.GOOGLE_PAY), // the full list of available payment methods has been provided
            gatewayId = "gatewayId", // gatewayId of the store for Google Pay payments (required if payment methods include Google Pay)
            customReturnUrl = "https://custom.redirect.url", // url of the page (only https is supported) that the user should be returned to after completing 3ds.
            userPhoneNumber = "+79041234567", // user's phone number for autofilling the user phone number field in SberPay. Supported data format: "+7XXXXXXXXXX"
            authCenterClientId = "example_authCenterClientId" // ID received upon registering the app on the https://yookassa.ru website
        )
        val intent = createTokenizeIntent(this, paymentParameters)
        startActivityForResult(intent, REQUEST_CODE_TOKENIZE)
    }*/
}

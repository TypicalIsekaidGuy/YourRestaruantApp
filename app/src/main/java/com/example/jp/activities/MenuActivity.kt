package com.example.jp.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.runtime.mutableStateOf
import com.example.jp.ui.screen.MenuScreen
import com.example.jp.data.bin.BinDao
import com.example.jp.data.bin.BinDatabase
import com.example.jp.data.products.Products
import com.example.jp.data.products.ProductsDao
import com.example.jp.data.products.ProductsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MenuActivity : ComponentActivity() {
    companion object{
        var products: List<Products> = emptyList()
        var chipList: List<Pair<String,Int>> = emptyList()
        var productsDao: ProductsDao? = null
        var binDao: BinDao? = null
        var supplements: List<Products> = emptyList()
        fun getProducts(context: Context): List<Products>{
            if(products.isEmpty())
                products = getproductsDao(context).getAllProducts()
            return products
        }
        fun getChips(context: Context): List<Pair<String,Int>>{
            if(chipList.isEmpty()){
                val chipListing: MutableList<Pair<String,Int>> = mutableListOf()
                val chips = getproductsDao(context).getChips()
                var str: String? = null
                for (i in chips){
                    if(str != i.type){
                        chipListing.add(Pair(i.type,i.id))
                        str = i.type
                    }
                }
                chipList = chipListing
            }
            return chipList
        }
        fun getproductsDao(context: Context): ProductsDao{
            if(productsDao==null)
                productsDao = ProductsDatabase.getInstance(context.applicationContext).productsDao
            return productsDao!!
        }
        fun getBinDao(context: Context): BinDao{
            if(binDao==null)
                binDao = BinDatabase.getInstance(context.applicationContext).binDao
            return binDao!!
        }
        fun getSupplements(context: Context): List<Products>{
            if(supplements.isEmpty()){
                var tempList = mutableListOf<Products>()
                for (i in getProducts(context)){
                    if(i.type == "Sause"||i.type == "Desert"){
                        tempList.add(i)
                    }
                }
                supplements = tempList
            }
            return supplements
        }
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productsState = mutableStateOf(emptyList<Products>())
        val trueChipState = mutableStateOf(emptyList<Pair<String,Int>>())

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            val chip = withContext(Dispatchers.IO) {
                getChips(applicationContext)
            }
            trueChipState.value = chip
            val products = withContext(Dispatchers.IO) {
                getProducts(applicationContext)
            }
            productsState.value = products
        }
        setContent {
            MenuScreen(productsState.value,trueChipState.value, applicationContext, getBinDao(applicationContext))
        }
    }

/*@Composable
fun CreateButton() {
    Box(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxSize(),
    contentAlignment = Alignment.BottomCenter){
    Button(onClick = { handleClick() }) {

            }
        }
    }


    @Composable
    fun handleClick(){
        CreatePicture(switch = switch)
        switch++
    }*/
}
/*

@Composable
fun CreatePicture(switch: Int) {
    var alignment: Alignment = Alignment.TopStart
    when(switch){
        0-> alignment = Alignment.TopStart
        1-> alignment = Alignment.TopEnd
        2-> alignment = Alignment.CenterStart
        3-> alignment = Alignment.CenterEnd
        4-> alignment = Alignment.BottomStart
        5-> alignment = Alignment.BottomEnd
        else -> Log.d("S","ssss")
    }
    Box(contentAlignment = alignment, modifier = Modifier.fillMaxSize()){
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(20.dp)
            .shadow(10.dp, RoundedCornerShape(10.dp))
    ) {
        Box(modifier = Modifier
            .height(200.dp)
            .width(160.dp)){
            Image(painter = painterResource(id = R.drawable.nagger), contentDescription = "Just some Picture")
            Box(modifier = Modifier
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 400f,
                        endY = 600f
                    )
                )
                .fillMaxSize()

            )
            Text(text = "It's a bottlesadasdsaadasdsasdasd",  modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp))
        }
    }
    }
}
@Composable
fun MyScreen() {
    val scaffoldState = rememberScaffoldState()
    var textFeildState by remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
            ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            TextField(
                value = textFeildState,
                onValueChange = { textFeildState = it  },
                label = { Text(text = "Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch { scaffoldState.snackbarHostState.showSnackbar("Sup niga") }

                }
            ) {
                Text(text = "Show Snackbar")
            }
        }
    }
}
*/

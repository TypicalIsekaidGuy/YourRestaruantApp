package com.example.jp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jp.activities.BinActivity
import com.example.jp.activities.MenuActivity
import com.example.jp.activities.ProfileActivity
import com.example.jp.data.bin.BinDao
import com.example.jp.data.products.Bin
import com.example.jp.data.products.Products
import com.example.jp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalFoundationApi
@Composable
fun MenuScreen(db: List<Products>, chipDB: List<Pair<String,Int>>, context: Context, dao: BinDao){
    Box(
        modifier = Modifier
            .background(DeepDark)
            .fillMaxSize()
    ){
        var selectedChipIndex by remember {
            mutableStateOf(1)
        }
        MenuTopBar()
        FeatureSection(features = db,selectedChipIndex, dao)
        ChipSection(chipDB, onChipSelected = {index -> selectedChipIndex = index })
        BottomMenu(items = listOf(
            BottomMenuContent("Menu", R.drawable.pizza_24, false, MenuActivity::class),
            BottomMenuContent("Bin", R.drawable.baseline_shopping_bag_24,true, BinActivity::class),
            BottomMenuContent("Profile", R.drawable.face_24, true, ProfileActivity::class)
        ), modifier = Modifier.align(Alignment.BottomCenter), context = context, initialSelectedItemIndex = 0)
    }
}




@Composable
fun MenuTopBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DeepDark)
            .padding(vertical = 15.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 10.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "SearchButton",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {

                    }
                    .align(alignment = Alignment.Center)
            )
        }

        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .widthIn(40.dp)
                .padding(horizontal = 10.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.baseline_shopping_bag_24),
                contentDescription = "BuyBagButton",
                modifier = Modifier
                    .clickable {

                    }
                    .size(40.dp)
            )
        }

        }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ){
        Text(
            text = "Menu",
            color = TextWhite,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.Center)


        )
    }
}

@Composable
fun ChipSection(
    chips: List<Pair<String,Int>>,onChipSelected: (Int) -> Unit){
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    Box(modifier = Modifier.padding(vertical = 100.dp).padding(end = 15.dp)){
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                        onChipSelected(chips[it].second-1)
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonDarkOrange
                        else ButtonDarkOut,
                        shape = CircleShape
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it].first,
                    color = if (selectedChipIndex == it) TextSelectedOrange else TextGray)
            }
        }
    }}
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Products>,  selectedChipIndex: Int, db: BinDao) {
    val scrollState = rememberLazyGridState()
    /*val features by*/
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight() // Expand to full height
            .padding(top = 180.dp),// , // Adjust the padding as needed
        verticalArrangement = Arrangement.Bottom // Align content to the bottom
    ) {
        Log.d("feafa",features.size.toString())

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 80.dp),
            modifier = Modifier.fillMaxWidth(),
            state = scrollState
        ) {
            items(features.size) {
                FeatureItem(db = db, feature = features[it])
                LaunchedEffect(selectedChipIndex) {
                    val id = selectedChipIndex.coerceIn(0, features.size - 1)
                    scrollState.animateScrollToItem(id)
                }
            }
        }
    }
}
@Composable
fun FeatureItem(db: BinDao, feature: Products) {
    val bitmap: Bitmap? = BitmapFactory.decodeByteArray(feature.icon, 0, feature.icon.size)
    val imageBitmap: ImageBitmap? = bitmap?.asImageBitmap()
    Row(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            imageBitmap?.let {
            Image(
                bitmap = it,
                contentDescription = feature.description,
                modifier = Modifier.fillMaxSize()
            )
        }
            if (feature.isNew) {
                NewIndicator()
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, top = 10.dp)
        ) {
            Text(text = feature.tittle, color = TextWhite, fontSize = 20.sp)
            Text(text = feature.description,color = TextWhite, modifier = Modifier.padding())
            Box(modifier = Modifier
                .padding(vertical = 6.dp)
                .clickable {

                    insertInBin(db, feature)
                     }){
            Box(
                modifier = Modifier
                    .background(ButtonDarkOrange, shape = CircleShape)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            ) {
                Text(
                    text = "$" + feature.price,
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }}
        }
    }
}
fun insertInBin(db: BinDao, products: Products){
    val scope = CoroutineScope(Dispatchers.IO)

    scope.launch {
        val list = db.getAllProducts()
        var mutableList = mutableListOf<String>()
        var foundProduct: Bin? = null
        for (i in list){
            mutableList.add(i.product.tittle)
            if (i.product.tittle == products.tittle) {
                foundProduct = i
            }

        }
        if(foundProduct!=null){
            db.deleteProduct(Bin(foundProduct.id, products, foundProduct.quantity))
            db.insertProduct(Bin(foundProduct.id, products, foundProduct.quantity+1))
        }
        else
            db.insertProduct(Bin(list.size, products,1))
        Log.d("YESSSSS",db.getAllProducts().size.toString())
    }
}
@Composable
fun NewIndicator() {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(Red, shape = CircleShape)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "New",
            color = TextWhite,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}




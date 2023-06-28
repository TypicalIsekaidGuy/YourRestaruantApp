package com.example.jp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jp.data.news.News
import com.example.jp.data.onSale.OnSale
import com.example.jp.data.products.Products
import com.example.jp.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(db: MutableState<List<News>>, db2: MutableState<List<OnSale>>){
    Box(
        modifier = Modifier
            .background(DeepDark)
            .fillMaxSize()
    ){
        ProfileTopBar()
        DeliveryOptions()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 180.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 80.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    OnSaleSection(db2.component1())
                }
                item {
                    NewsSection(db.component1())
                }
                item {
                    TechSupportSection()
                }
            }
        }

        BottomMenu(items = listOf(
            BottomMenuContent("Menu", R.drawable.pizza_24, false),
            BottomMenuContent("Meditate", R.drawable.ic_launcher_background, false),
            BottomMenuContent("Sleep", R.drawable.ic_launcher_background, false),
            BottomMenuContent("Bin", R.drawable.baseline_shopping_bag_24, false),
            BottomMenuContent("Profile", R.drawable.face_24, true),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun ProfileTopBar(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ){
        Text(
            text = "Profile",
            color = TextWhite,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.Center)


        )
    }
}
@Composable
fun DeliveryOptions(
/*    onHomeDeliveryClick: () -> Unit,
    onRestaurantClick: () -> Unit,
    onAnyClick: () -> Unit*/
) {
    var selectedOption by remember { mutableStateOf(DeliveryOption.None) }
    val backgroundColor1 = if (selectedOption == DeliveryOption.HomeDelivery) TextSelectedOrange else Orange
    val backgroundColor2 = if (selectedOption == DeliveryOption.Deliver) TextSelectedOrange else Orange
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp)
            .padding(top = 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .clickable { selectedOption = DeliveryOption.HomeDelivery }
                    .background(backgroundColor1, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Home Delivery",
                    color = TextWhite,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 16.sp
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .clickable { selectedOption = DeliveryOption.Deliver }
                    .background(backgroundColor2, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Restaurant",
                    color = TextWhite,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 35.dp)
            .padding(top = 140.dp)
            .padding(start = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .clickable { }
                    .background(Orange, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Find a place",
                    color = TextWhite,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}



enum class DeliveryOption {
    None,
    HomeDelivery,
    Deliver
}

@ExperimentalFoundationApi
@Composable
fun OnSaleSection(onSaleItems: List<OnSale>) {
    Box() {
        Text(
            text = "On-Sale",
            fontSize = 64.sp,
            color = TextWhite,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 70.dp)
        ) {
            items(onSaleItems.size) {
                OnSaleItem(OnSaleItem = onSaleItems[it])
            }
        }
    }
}
@Composable
fun OnSaleItem(OnSaleItem: OnSale) {
    val bitmap: Bitmap? = BitmapFactory.decodeByteArray(OnSaleItem.image, 0, OnSaleItem.image.size)
    val imageBitmap: ImageBitmap? = bitmap?.asImageBitmap()
    Box(
        modifier = Modifier
            .clickable { /* Handle news item click */ }
            .border(width = 2.dp, color = Orange, shape = RoundedCornerShape(20.dp))
            .padding(8.dp)
            .width(150.dp)
            .aspectRatio(0.7f)
    ) {
        imageBitmap?.let {
            Image(
                bitmap = it,
                contentDescription = OnSaleItem.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = OnSaleItem.tittle,
                style = TextStyle(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(),
                fontSize = 20.sp,
                color = TextWhite
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NewsSection(news: List<News>) {
    Box() {
        Text(
            text = "News",
            fontSize = 64.sp,
            color = TextWhite,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 80.dp)
    ) {
        items(news.size) {
            NewsItem(newsItem = news[it])
        }
    }
    }
}

@Composable
fun NewsItem(newsItem: News) {
    val bitmap: Bitmap? = BitmapFactory.decodeByteArray(newsItem.image, 0, newsItem.image.size)
    val imageBitmap: ImageBitmap? = bitmap?.asImageBitmap()
    Box(
        modifier = Modifier
            .clickable { /* Handle news item click */ }
            .border(width = 2.dp, color = Orange, shape = RoundedCornerShape(20.dp))
            .padding(8.dp)
            .width(150.dp)
            .aspectRatio(0.7f)
    ) {
        imageBitmap?.let {
            Image(
                bitmap = it,
                contentDescription = newsItem.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = newsItem.tittle,
                style = TextStyle(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(),
                fontSize = 20.sp,
                color = TextWhite
            )
        }
    }
}

@Composable
fun TechSupportSection(){
    Box {
        Text(
            text = "Support",
            fontSize = 64.sp,
            color = TextWhite,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Box(
            modifier = Modifier.padding(top = 90.dp)
        ){
        Box(
            modifier = Modifier
                .background(ButtonDarkOrange, shape = CircleShape)
                .padding(vertical = 6.dp, horizontal = 15.dp)
                .fillMaxWidth()
                .clickable {  }
        ) {
            Text(
                text = "Chat",
                color = TextWhite,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    }
}

package com.example.jp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
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
import com.example.jp.data.products.Products
import com.example.jp.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(db: MutableState<List<News>>){
    Box(
        modifier = Modifier
            .background(DeepDark)
            .fillMaxSize()
    ){
        ProfileTopBar()
        DeliveryOptions()
        NewsSection(db.component1())
        /*OnSale()*/
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
fun DeliveryOptions() {
    var selectedOption by remember { mutableStateOf(DeliveryOption.None) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            RadioButtonOption(
                text = "Home Delivery",
                selected = selectedOption == DeliveryOption.HomeDelivery,
                onClick = { selectedOption = DeliveryOption.HomeDelivery }
            )

            RadioButtonOption(
                text = "Deliver",
                selected = selectedOption == DeliveryOption.Deliver,
                onClick = { selectedOption = DeliveryOption.Deliver }
            )
        }
    }
}

@Composable
fun RadioButtonOption(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 8.dp)
            .clickable { onClick }
            .background(
                color = if (selected) ButtonDarkOrange else ButtonDarkOut,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = TextWhite,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}

enum class DeliveryOption {
    None,
    HomeDelivery,
    Deliver,
    Restaurant
}

@ExperimentalFoundationApi
@Composable
fun OnSaleSection(items: List<OnSale>) {
/*    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(newsList) { newsItem ->
            NewsItemCard(newsItem)
        }
    }*/

/*    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight() // Expand to full height
            .padding(top = 40.dp),// , // Adjust the padding as needed
        verticalArrangement = Arrangement.Bottom // Align content to the bottom
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 80.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items.size) {
                OnSaleItem(onSale = items[it])
            }
        }
    }*/
}

@Composable
fun OnSaleItem(onSale: OnSale) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Image(
                painter = painterResource(id = onSale.imageId),
                contentDescription = onSale.description,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NewsSection(news: List<News>) {
    Box(modifier = Modifier.padding(top = 100.dp)){
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = newsItem.tittle,
                style = TextStyle(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding()
            )
        }
        imageBitmap?.let {
            Image(
                bitmap = it,
                contentDescription = newsItem.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

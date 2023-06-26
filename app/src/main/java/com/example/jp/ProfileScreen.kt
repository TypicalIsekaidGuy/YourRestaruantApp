package com.example.jp

import android.graphics.drawable.shapes.OvalShape
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jp.BottomMenuContent
import com.example.jp.R
import com.example.jp.data.Products
import com.example.jp.data.ProductsDao
import com.example.jp.standardQuadFromTo
import com.example.jp.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(){
    Box(
        modifier = Modifier
            .background(DeepDark)
            .fillMaxSize()
    ){
        ProfileTopBar()
        DeliveryOptions()
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight() // Expand to full height
            .padding(top = 40.dp),// , // Adjust the padding as needed
        verticalArrangement = Arrangement.Bottom // Align content to the bottom
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 80.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items.size) {
                OnSaleItem(onSale = items[it])
            }
        }
    }
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


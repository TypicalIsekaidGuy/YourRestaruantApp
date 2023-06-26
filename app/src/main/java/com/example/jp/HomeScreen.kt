package com.example.jp

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.ui.graphics.*
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
import androidx.room.Room
import com.example.jp.BottomMenuContent
import com.example.jp.R
import com.example.jp.data.Products
import com.example.jp.data.ProductsDatabase
import com.example.jp.standardQuadFromTo
import com.example.jp.ui.theme.*
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

@ExperimentalFoundationApi
@Composable
fun MenuScreen(db: MutableState<List<Products>>){
    Box(
        modifier = Modifier
            .background(DeepDark)
            .fillMaxSize()
    ){
        MenuTopBar()
        ChipSection(chips = listOf("Big Pizza", "Small Pizza", "Depression","burgir","sticker"))
        FeatureSection(features = db.component1())
        BottomMenu(items = listOf(
            BottomMenuContent("Menu", R.drawable.pizza_24, true),
            BottomMenuContent("Meditate", R.drawable.ic_launcher_background, false),
            BottomMenuContent("Sleep", R.drawable.ic_launcher_background, false),
            BottomMenuContent("Bin", R.drawable.baseline_shopping_bag_24, false),
            BottomMenuContent("Profile", R.drawable.face_24, false),
        ), modifier = Modifier.align(Alignment.BottomCenter))
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
            text = "Меню",
            color = TextWhite,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.Center)


        )
    }
}

@Composable
fun ChoosingDestination(){
/*    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .padding(vertical = 70.dp)
            .background(TextWhite)
            .fillMaxWidth()
    ){
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .size(width = 50.dp, height = 20.dp)
                .padding(vertical = 70.dp)
                .background(Color.Black)
        ){

        }
    }*/
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 70.dp, horizontal = 10.dp)
            .background(TextWhite, shape = RoundedCornerShape(10.dp))
    ){
        Column() {

            Row(
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clickable { }
                        .background(Color.Black, shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 20.dp)
                ){
                    Text(text = "На доставку",
                        color = TextWhite,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.Center))
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clickable { }
                        .background(Color.Black, shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 20.dp)
                ){
                    Text(text = "В зале",
                        color = TextWhite,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.Center))
                }
            }

            Row(
            ){
                Box(
                    modifier = Modifier
                        .clickable { }
                        .background(Color.Black)
                        .padding(bottom = 15.dp)
                        .clip(RoundedCornerShape(30.dp))
                ){
                    Text(text = "На доставку",
                        color = TextWhite,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.Center))
                }
                Box(
                    modifier = Modifier
                        .clickable { }
                        .background(Color.Black)
                        .padding(start = 15.dp)
                ){
                    Text(text = "В зале",
                        color = TextWhite,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    Box(modifier = Modifier.padding(vertical = 170.dp)){
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                        Log.d("HELLLO",selectedChipIndex.toString())
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonDarkOrange
                        else ButtonDarkOut,
                        shape = CircleShape
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it],
                    color = if (selectedChipIndex == it) TextSelectedOrange else TextGray)
            }
        }
    }}
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Meditation • 3-10 min",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonDarkOrange)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Play",
                tint = TextWhite,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Products>) {
    val scrollState = rememberLazyListState()
    /*val features by*/
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight() // Expand to full height
            .padding(top = 250.dp),// , // Adjust the padding as needed
        verticalArrangement = Arrangement.Bottom // Align content to the bottom
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 80.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }

/*    LaunchedEffect(selectedChipInt) {
        val itemIndex = selectedChipInt.coerceIn(0, features.size - 1)
        scrollState.animateScrollToItem(itemIndex)
    }*/
}
@Composable
fun FeatureItem(feature: Products) {
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
                .padding(start = 16.dp)
        ) {
            Text(text = feature.tittle, color = TextWhite)
            Text(text = feature.description,color = TextWhite, modifier = Modifier.padding())
            Box(modifier = Modifier
                .padding(vertical = 6.dp)
                .clickable { /* Handle button click */ }){
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




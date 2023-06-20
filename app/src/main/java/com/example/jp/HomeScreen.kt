package com.example.jp

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
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
import com.example.jp.Feature
import com.example.jp.R
import com.example.jp.standardQuadFromTo
import com.example.jp.ui.theme.*


@ExperimentalFoundationApi
@Composable
fun MenuScreen(){
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ){
        MenuTopBar()
        DeliveryOptions()
        ChipSection(chips = listOf("Big Pizza", "Small Pizza", "Depression","burgir","sticker"))
        FeatureSection(
            features = listOf(
                Feature(
                    title = "Pizza",
                    description = "Beutiful pizza with beutiful toppings, and its good, very good i must say! For true enjoyers for the art of cooking!",
                    iconId = R.drawable.pizza1,
                    isNew = true,
                    price = 100
                ),
                Feature(
                    title = "Pizza",
                    description = "Beutiful pizza with beutiful toppings, and its good, very good i must say! For true enjoyers for the art of cooking!",
                    iconId = R.drawable.pizza1,
                    isNew = true,
                    price = 1
                ),
                Feature(
                    title = "Pizza",
                    description = "Beutiful pizza with beutiful toppings, and its good, very good i must say! For true enjoyers for the art of cooking!",
                    iconId = R.drawable.pizza2,
                    isNew = true,
                    price = 1000000
                ),
                Feature(
                    title = "Pizza",
                    description = "Beutiful pizza with beutiful toppings, and its good, very good i must say! For true enjoyers for the art of cooking!",
                    iconId = R.drawable.pizza2,
                    isNew = false,
                    price = 100
                )
            )
        )
        BottomMenu(items = listOf(
            BottomMenuContent("Menu", R.drawable.pizza_24),
            BottomMenuContent("Meditate", R.drawable.ic_launcher_background),
            BottomMenuContent("Sleep", R.drawable.ic_launcher_background),
            BottomMenuContent("Bin", R.drawable.baseline_shopping_bag_24),
            BottomMenuContent("Profile", R.drawable.face_24),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}


@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Pizza",
                        description = "Beutiful pizza with beutiful toppings, and its good, very good i must say! For true enjoyers for the art of cooking!",
                        iconId = R.drawable.scr,
                        isNew = true,
                        price = 100
                    ),
                    Feature(
                        title = "Pizza",
                        description = "Beutiful pizza with beutiful toppings, and its good, very good i must say! For true enjoyers for the art of cooking!",
                        iconId = R.drawable.scr,
                        isNew = true,
                        price = 1
                    ),
                    Feature(
                        title = "Pizza",
                        description = "Beutiful pizza with beutiful toppings, and its good, very good i must say! For true enjoyers for the art of cooking!",
                        iconId = R.drawable.scr,
                        isNew = true,
                        price = 1000000
                    ),
                    Feature(
                        title = "Pizza",
                        description = "Beutiful pizza with beutiful toppings, and its good, very good i must say! For true enjoyers for the art of cooking!",
                        iconId = R.drawable.scr,
                        isNew = false,
                        price = 100
                    )
                )
            )
        }
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_launcher_background),
            BottomMenuContent("Meditate", R.drawable.ic_launcher_background),
            BottomMenuContent("Sleep", R.drawable.ic_launcher_background),
            BottomMenuContent("Music", R.drawable.ic_launcher_background),
            BottomMenuContent("Profile", R.drawable.ic_launcher_background),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@Composable
fun MenuTopBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DeepBlue)
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
            .background(Color.White)
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
            .background(Color.White, shape = RoundedCornerShape(10.dp))
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
fun GreetingSection(
    name: String = "Philipp"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good morning, $name",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.body1
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
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
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue,
                        shape = CircleShape
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
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
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 240.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(feature: Feature) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Image(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.description,
                modifier = Modifier.fillMaxSize()
            )
            if (feature.isNew) {
                NewIndicator()
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(text = feature.title, color = Color.White)
            Text(text = feature.description,color = Color.White, modifier = Modifier.padding())
            Box(modifier = Modifier
                .padding(vertical = 6.dp)
                .clickable { /* Handle button click */ }){
            Box(
                modifier = Modifier
                    .background(ButtonBlue, shape = CircleShape)
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
            .background(Color.Red, shape = CircleShape)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "New",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun DeliveryOptions() {
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
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .clickable { }
                    .background(Color.Blue,  shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Home Delivery",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .clickable { }
                    .background(Color.Blue, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Deliver",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .padding(60.dp)
            .padding(vertical = 60.dp)
            .size(width = 300.dp, height = 50.dp)
            .clickable { }
            .background(Color.Blue, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Restaurant",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}


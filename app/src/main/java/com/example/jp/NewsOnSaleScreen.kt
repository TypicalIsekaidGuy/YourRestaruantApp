package com.example.jp

import android.content.Context
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
import com.example.jp.activities.MenuActivity
import com.example.jp.data.news.News
import com.example.jp.data.onSale.OnSale
import com.example.jp.data.products.Products
import com.example.jp.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun NewsOnSaleScreen(
    db: MutableState<List<News>>,
    db2: MutableState<List<OnSale>>,
    context: Context,
    index: Int,
    isNews: Boolean
) {
    Box(
        modifier = Modifier
            .background(DeepDark)
            .fillMaxSize()
    ) {
        Log.d("ssssssssss",db.value.firstOrNull().toString())
        Log.d("ssssssssss",db2.component1().toString())
        Log.d("ssssssssss",db2.component1()[index].toString())
        val byteArray: ByteArray
        val tittle: String
        val description: String
        if (isNews){
            val item = db.component1()[index]
            byteArray = item.image
            tittle = item.tittle
            description = item.description
        }
        else{
            val item = db2.component1()[index]
            byteArray = item.image
            tittle = item.tittle
            description = item.description
        }
        val bitmap: Bitmap? = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        val imageBitmap: ImageBitmap? = bitmap?.asImageBitmap()
        imageBitmap?.let {
            Image(
                bitmap = it,
                contentDescription = description,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Content
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            // Exit Button
            IconButton(
                onClick = { /* Handle exit button click */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                    contentDescription = "Exit"
                )
            }

            // Title
            Text(
                text = tittle,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    fontFamily = FontFamily.SansSerif
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            // Description
            Text(
                text = description,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextWhite,
                    fontFamily = FontFamily.SansSerif
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Other content goes here...

        }
    }
}

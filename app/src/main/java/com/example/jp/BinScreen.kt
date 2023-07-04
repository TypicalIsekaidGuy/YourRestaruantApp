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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jp.activities.MenuActivity
import com.example.jp.activities.ProfileActivity
import com.example.jp.data.bin.BinDao
import com.example.jp.data.products.Bin
import com.example.jp.data.products.Products
import com.example.jp.ui.theme.ButtonDarkOrange
import com.example.jp.ui.theme.DeepDark
import com.example.jp.ui.theme.LighterDark
import com.example.jp.ui.theme.TextWhite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun BinScreen(dao: BinDao, db: MutableState<MutableList<Bin>>, context: Context, supplements:List<Products>){
    val total: State<Int> = remember {
        derivedStateOf {
            db.value.sumOf { it.product.price*it.quantity }
        }
    }
    val saucesCount: State<Int> = remember {
        derivedStateOf {
            db.value.count { it.product.type == "Sause" }
        }
    }
    val saucesTotal: State<Int> = remember {
        derivedStateOf {
            db.value.sumOf { if (it.product.type =="Sause") it.product.price*it.quantity else 0 }
        }
    }
    val desertsCount: State<Int> = remember {
        derivedStateOf {
            db.value.count { it.product.type == "Desert" }
        }
    }
    val desertsTotal: State<Int> = remember {
        derivedStateOf {
            db.value.sumOf { if (it.product.type =="Desert") it.product.price*it.quantity else 0 }
        }
    }
    Box(
        modifier = Modifier
            .background(DeepDark)
            .fillMaxSize()
    ){
        BinTopBar()
        /*if(db.isEmpty())*/
        LazyColumn (modifier = Modifier.padding(top = 70.dp)){
            item {
                BinSection(dao, db,total.value)
            }
            item {
                SupplementsSection(dao,db,supplements)
            }
            item {
                TotalSection(saucesCount.value,saucesTotal.value, desertsCount.value, desertsTotal.value, false,100)
            }
        }
        Column (modifier = Modifier.align(Alignment.BottomCenter)){
            BuyButton(total.value)
            BottomMenu(items = listOf(
                BottomMenuContent("Menu", R.drawable.pizza_24, true, MenuActivity::class),
                BottomMenuContent("Bin", R.drawable.baseline_shopping_bag_24,false, MenuActivity::class),
                BottomMenuContent("Profile", R.drawable.face_24, true, ProfileActivity::class)
            ), modifier = Modifier, context = context, initialSelectedItemIndex = 1)
        }
    }
}

@Composable
fun BinTopBar(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ){
        Text(
            text = "Bin",
            color = TextWhite,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.Center)


        )
    }
}
@Composable
fun BinSection(dao: BinDao, items: MutableState<MutableList<Bin>>,total: Int){
    Column{
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        ){
            Text(
                text = "${items.value.size} products for $total $",
                color = TextWhite,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        items.value.forEach {bin ->
            BinItem(dao,items, bin)

        }
    }
}
@Composable
fun BinItem(dao:BinDao,db: MutableState<MutableList<Bin>>, binItem: Bin) {
    val bitmap: Bitmap? = BitmapFactory.decodeByteArray(binItem.product.icon, 0, binItem.product.icon.size)
    val imageBitmap: ImageBitmap? = bitmap?.asImageBitmap()
    Column(modifier = Modifier.padding(bottom = 30.dp)) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Box(
                modifier = Modifier
            ) {
            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
            }
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(text = binItem.product.tittle,
                    color = TextWhite,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(top = 15.dp))
            }
        }
        Row(modifier = Modifier.padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "${binItem.product.price*binItem.quantity}$",
                color = TextWhite,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp),
                textAlign = TextAlign.Center)
            PlusMinusButton(dao,db, binItem)
        }
    }
}

@Composable fun PlusMinusButton(dao: BinDao,db: MutableState<MutableList<Bin>>, binItem: Bin){
    Box{
    Box(modifier = Modifier
        .padding(vertical = 6.dp)
        .padding(start = 10.dp)){
        Box(
            modifier = Modifier
                .background(ButtonDarkOrange, shape = CircleShape)
                .padding(vertical = 6.dp, horizontal = 30.dp)
        ) {
            Text(
                text = binItem.quantity.toString(),
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

        Box(modifier = Modifier
            .padding(vertical = 6.dp)
            .clickable {
                deleteItem(dao, db, db.value.indexOf(binItem))
            }){
            Box(
                modifier = Modifier
                    .background(ButtonDarkOrange, shape = CircleShape)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            ) {
                Text(
                    text = "-",
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    Box(modifier = Modifier
        .padding(start = (52 + (binItem.quantity.toString().length - 1) * 10).dp)
        .padding(vertical = 6.dp)
        .clickable { insertItem(dao, db, db.value.indexOf(binItem)) }){
        Box(
            modifier = Modifier
                .background(ButtonDarkOrange, shape = CircleShape)
                .padding(vertical = 6.dp, horizontal = 15.dp)
        ) {
            Text(
                text = "+",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    }
}
fun insertItem(dao: BinDao,db: MutableState<MutableList<Bin>>, id: Int){

    val scope = CoroutineScope(Dispatchers.IO)

    scope.launch {
        val list = dao.getAllProducts()
        val bin = list[id]
        val newBin = Bin(bin.id, bin.product, bin.quantity + 1)
        dao.updateProduct(newBin)
        val updatedList = db.value.toMutableList().apply {
            val index = indexOfFirst { it.id == newBin.id }
            if (index != -1) {
                set(index, newBin)
            }
        }
        db.value = updatedList
    }
}
fun deleteItem(dao: BinDao,db: MutableState<MutableList<Bin>>, id: Int){
    val scope = CoroutineScope(Dispatchers.IO)

    scope.launch {
        val list = db.value as List<Bin>
            val bin = list[id]
            if(bin.quantity>1){
                val newBin = Bin(bin.id, bin.product, bin.quantity-1)
                if(dao.getAllProducts().size>id){
                    dao.updateProduct(newBin)
                }
                val updatedList = db.value.toMutableList().apply {
                    val index = indexOfFirst { it.id == newBin.id }
                    if (index != -1) {
                        set(index, newBin)
                    }
                }
                db.value = updatedList
            }
            else
            {
                val updatedList = db.value.toMutableList().apply {
                    remove(bin)
                }
                db.value=updatedList

                if(dao.getAllProducts().size>id)
                    dao.deleteProduct(bin)
            }
        }

    }
@Composable
fun SupplementsSection(dao: BinDao,db: MutableState<MutableList<Bin>>,supplements: List<Products>){
    Column{
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        ){
            Text(
                text = "Add to the order?",
                color = TextWhite,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        LazyRow(/*rows = GridCells.Fixed(1)*/){
            items(supplements.size){
                SupplementItem(dao,db,supplementItem = supplements[it])
            }
        }
    }
}

@Composable
fun SupplementItem(dao: BinDao,db: MutableState<MutableList<Bin>>,supplementItem: Products) {
    val bitmap: Bitmap? = BitmapFactory.decodeByteArray(supplementItem.icon, 0, supplementItem.icon.size)
    val imageBitmap: ImageBitmap? = bitmap?.asImageBitmap()
    Row() {
        Box() {
            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }
        }
        Column(modifier = Modifier.padding(start = 5.dp, end = 30.dp)){
            Text(text = supplementItem.tittle, color = TextWhite, fontSize = 20.sp)
            Box(modifier = Modifier
                .padding(vertical = 6.dp)
                .clickable { createItem(dao,db,supplementItem) }){
                Box(
                    modifier = Modifier
                        .background(ButtonDarkOrange, shape = CircleShape)
                        .padding(vertical = 6.dp, horizontal = 15.dp)
                ) {
                    Text(
                        text = "$" + supplementItem.price,
                        color = TextWhite,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }}
        }
    }
}
fun createItem(dao: BinDao,db: MutableState<MutableList<Bin>>, product: Products){

    val scope = CoroutineScope(Dispatchers.IO)

    scope.launch {
        var products: MutableList<Products> = mutableListOf()
        for (i in db.value){
            products.add(i.product)
        }
        if(!products.contains(product)){
            var id: Int
            if(db.value.isEmpty())
                id = 0
            else
                id = db.value.last().id+1
            val newBin = Bin(id, product,1)

            dao.insertProduct(newBin)
            val updatedList = db.value.toMutableList().apply {
                add(newBin)
            }
            db.value = updatedList
        }

    }
}
@Composable
fun TotalSection(sausesCount: Int, sausesTotal: Int, desertsCount: Int,desertsTotal: Int,isDeliveryNeeded: Boolean, deliveryPrice: Int){
    Column(modifier = Modifier.padding(top = 50.dp, bottom = 150.dp)) {
        TotalItem("$sausesCount souses","$sausesTotal $")
        TotalItem("$desertsCount souses","$desertsTotal $")
        TotalItem("Delivery", if (isDeliveryNeeded) "$deliveryPrice" else "Free")
    }
}
@Composable
fun TotalItem(text1: String,text2: String){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween){


        Text(
            text = text1,
            color = TextWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text2,
            color = TextWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }
}
@Composable /*this could be scrollable so i need to fix it*/
fun BuyButton(total: Int){
    Box (modifier = Modifier
        .background(DeepDark)){
        Column(modifier = Modifier.background(DeepDark)){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 10.dp, height = 3.dp)
                        .background(color = LighterDark)
                )

                Box(
                    modifier = Modifier
                        .background(ButtonDarkOrange, shape = CircleShape)
                        .padding(vertical = 6.dp, horizontal = 15.dp)
                        .fillMaxWidth()
                        .clickable { }
                ) {
                    Text(
                        text = "Buy for $total $",
                        color = TextWhite,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
        }
    }
}
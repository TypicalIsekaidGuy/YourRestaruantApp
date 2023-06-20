package com.example.jp

import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jp.ui.theme.JPTheme
import com.example.jp.ui.theme.Shapes
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    var switch = 0
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuScreen()
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

package com.example.jp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import com.example.jp.MenuScreen


class MenuActivity : ComponentActivity() {
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

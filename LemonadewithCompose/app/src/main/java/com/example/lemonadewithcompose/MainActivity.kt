package com.example.lemonadewithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadewithcompose.ui.theme.LemonadeWithComposeTheme
import com.example.lemonadewithcompose.ui.theme.LightBlue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeWithComposeTheme {
                LemonadeApp()

            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp(){
    var pageNumber by remember {
        mutableStateOf(1)
    }
    var squeezeCnt by remember {
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(pageNumber){
            1 -> {
                TextAndImage(
                    textResourceId = R.string.tap_lemon_tree,
                    imgResourceId = R.drawable.lemon_tree,
                    onClickFunc = {
                        pageNumber = 2
                        squeezeCnt = (2..4).random()
                    }
                )
            }
            2 -> {
                TextAndImage(
                    textResourceId = R.string.tap_lemon,
                    imgResourceId = R.drawable.lemon_squeeze,
                    onClickFunc = {
                        squeezeCnt--
                        if(squeezeCnt==0){
                            pageNumber = 3
                        }
                    }
                )
            }
            3 -> {
                TextAndImage(
                    textResourceId = R.string.tap_lemonade,
                    imgResourceId = R.drawable.lemon_drink,
                    onClickFunc = {
                        pageNumber = 4
                    }
                )
            }
            else -> {
                TextAndImage(
                    textResourceId = R.string.tap_empty_glass,
                    imgResourceId = R.drawable.lemon_restart,
                    onClickFunc = {
                        pageNumber = 1
                    }
                )
            }
        }
    }
}
@Composable
fun TextAndImage(
    textResourceId: Int,
    imgResourceId: Int,
    onClickFunc: () -> Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = textResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imgResourceId),
            contentDescription = null,
            modifier = Modifier
                .clickable( onClick =onClickFunc )
                .clip(RoundedCornerShape(4.dp))
                .border(width = 2.dp, color = LightBlue, shape = RectangleShape)
        )
    }
}
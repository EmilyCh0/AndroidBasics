package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.businesscard.ui.theme.BusinessCardTheme
import com.example.businesscard.ui.theme.primaryVariant
import com.example.businesscard.ui.theme.secondary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme(
            ) {
                // this.window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BusinessCard()
                }
            }
        }
    }
}


@Composable
fun BusinessCard(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.android_logo),
                contentDescription = null,
                Modifier.size(80.dp)
            )
            Text(
                text = stringResource(R.string.full_name),
                style = MaterialTheme.typography.h3
            )
            Text(
                text = stringResource(R.string.android_developer_extraordinaire),
                color = secondary
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 32.dp, bottom = 64.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Divider(color = Color.LightGray, thickness = 1.dp)
            DetailInfo(
                imageVector = Icons.Filled.Phone,
                content = "+11 (123) 444 555 666"
            )
            Divider(color = Color.LightGray, thickness = 1.dp)
            DetailInfo(
                imageVector = Icons.Filled.Share,
                content = "@AndroidDev"
            )
            Divider(color = Color.LightGray, thickness = 1.dp)
            DetailInfo(
                imageVector = Icons.Filled.Email,
                content = "jen.doe@android.com"
            )
        }
    }
}

@Composable
fun DetailInfo(
    imageVector: ImageVector,
    content: String
){
    Row(
        Modifier
            .padding(start = 64.dp, top = 4.dp, bottom = 4.dp)
    ) {
        Icon(imageVector, null, tint = secondary)
        Text(
            text = content,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}
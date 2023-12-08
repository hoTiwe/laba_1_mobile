package com.example.circle_wear.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.example.circle_wear.R
import com.example.circle_wear.presentation.navigation.Screen
import com.example.circle_wear.presentation.theme.Circle_wearTheme

@Composable
fun AuthScreen(
    navigationByRoute: (route: String) -> Unit
){
    val login = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val colorLogin = remember {
        mutableStateOf(Color.Black)
    }
    val colorPassword = remember {
        mutableStateOf(Color.Black)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(modifier = Modifier.size(64.dp), painter = painterResource(id = R.drawable.logo), contentDescription = null)
        BasicTextField(
            value = login.value,
            onValueChange = {
                login.value = it
                colorLogin.value = Color.Black
                colorPassword.value = Color.Black},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(textAlign = TextAlign.Center, color = colorLogin.value),
            decorationBox = {  innerTextField ->
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center){
                    if (login.value.isEmpty()) {
                        Text("Login", color = Color.Black)
                    }
                }
                innerTextField()  //<-- Add this
            }
        )
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.Black))
        BasicTextField(
            value = password.value,
            onValueChange = {
                password.value = it
                colorLogin.value = Color.Black
                colorPassword.value = Color.Black
            },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(textAlign = TextAlign.Center, color = colorPassword.value),
            decorationBox = {  innerTextField ->
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center){
                    if (password.value.isEmpty()) {
                        Text("Password", color = Color.Black)
                    }
                }
                innerTextField()  //<-- Add this
            }
        )
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.Black))
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            onClick = {
                val listLogin = listOf(
                    "qwerty@mail.ru",
                    "y",
                    "ew@gmail.com",
                    "eve@sfedu.ru",
                    "aga@sqwore.pixie"
                )
                val passwordLogin = listOf("qqq", "y", "qwe", "qwe", "gsda")
                val index = listLogin.indexOf(login.value)
                if (index == -1) {
                    colorLogin.value = Color.Red
                } else {
                    colorLogin.value = Color.Black
                    if (passwordLogin[index] != password.value) {
                        colorPassword.value = Color.Red
                    } else {
                        colorPassword.value = Color.Black
                        navigationByRoute(Screen.Main.route)
                    }
                }
            },

        ) {
            Text(
                text = "войти",
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
fun preview(){
    Circle_wearTheme {

        AuthScreen(navigationByRoute = {})
    }
}
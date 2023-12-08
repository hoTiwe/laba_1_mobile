package com.example.circle_wear.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.wear.compose.material.items
import com.example.circle_wear.presentation.theme.Circle_wearTheme
import kotlinx.coroutines.launch
import java.lang.Exception


@Composable
fun MainScreen(
){
    var recipes  by remember {
        mutableStateOf(listOf<Recipe>())
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(key1 = true){
        scope.launch{
            Log.d("","Start")
            val response = try {
                RetrofitInstance.api.getRecipes()
            }catch(e: Exception){
                Log.d("", e.toString())
                return@launch
            }
            Log.d("","Response $response")

            if (response.isSuccessful && response.body() != null){
                recipes = response.body()!!
                Log.d("","Recipes $recipes")
            }
        }
    }
    ScalingLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        this.apply {
            items(recipes) { recipe ->
                Log.d("", recipe.toString())
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 3.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {}
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillParentMaxHeight(),
                        verticalArrangement = Arrangement.Center) {
                        Text(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                            text = recipe.Name,
                            fontSize = 10.sp,
                            style = TextStyle(
                                lineHeight = 10.sp
                            )
                        )
                        Row {
                            Text(text = "Сложность: ${recipe.Difficulty}", fontSize = 8.sp)
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "Время: ${recipe.Time}", fontSize = 8.sp)
                        }
                    }
                }
            }

        }
    }
}

@Composable
@Preview
fun previewMain(){
    Circle_wearTheme {
        val recipe = Recipe(Calorie=1143, Time=20, Name="Феттучини с ветчиной и грибами в сливочном соусе, Ingredients=паста феттучини, шампиньоны, ветчина, сливочное масло, сливки, мука, сыр, соль, растительное масло, зелень", Difficulty=3)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 3.dp),
            onClick = {}
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                    text = recipe.Name, fontSize = 10.sp)
                Row {
                    Text(text = "Сложность: ${recipe.Difficulty}", fontSize = 8.sp)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Время: ${recipe.Time}", fontSize = 8.sp)
                }
            }
        }
    }
}
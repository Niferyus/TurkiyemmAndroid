package com.example.turkiyemcomposee.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.turkiyemcomposee.Constants.Constants
import com.example.turkiyemcomposee.models.City
import com.example.turkiyemcomposee.navigate
import com.example.turkiyemcomposee.services.API
import com.example.turkiyemcomposee.ui.theme.TurkiyemcomposeeTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun citysscreen(navController: NavController,citylist : List<City>) {

    var searchString by remember { mutableStateOf("") }

    Scaffold { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF7C65A9), // Mor
                            Color(0xFF96D4CA)  // Yeşil
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(12.dp)) {
                item {
                    Text("Şehirler",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp))
                }
                item {
                    TextField(value = searchString,
                        onValueChange = {
                        searchString = it
                    }, leadingIcon = {
                        Icon(Icons.Rounded.Search,"Search icon")
                        },
                        placeholder = { Text("Şehir girebilirsiniz...") },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                    )
                }
                items(citylist) { item: City ->
                    if (item.name.lowercase().contains(searchString.lowercase())){
                        cityButton(navController,item)
                    }

                }
            }

        }
    }
}

@Composable
fun cityButton(navController: NavController,city: City) {
    Button(
        onClick = {
            val cityJson = Uri.encode(Json.encodeToString(city))
            navController.navigate("cityDetails/$cityJson")
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = city.name,
            fontSize = 24.sp
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun cityPreview() {
    TurkiyemcomposeeTheme {
        citysscreen(listOf(City(1,"Sivas","","","",0.0,0.0)))
    }
}

 */
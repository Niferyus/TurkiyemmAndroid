package com.example.turkiyemcomposee.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.turkiyemcomposee.models.City

@Composable
fun citydetailscreen(city: City?)
{
    Column {
        Text("${city?.name}")
        Text("${city?.description}")
    }
}
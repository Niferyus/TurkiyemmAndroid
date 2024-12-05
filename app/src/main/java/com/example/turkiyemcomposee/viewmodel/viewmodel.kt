package com.example.turkiyemcomposee.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turkiyemcomposee.Constants.Constants
import com.example.turkiyemcomposee.models.City
import com.example.turkiyemcomposee.services.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class viewmodel: ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API::class.java)

    val citieslist = mutableStateOf<List<City>>(listOf())

    fun getdata() : List<City>{
        viewModelScope.launch(Dispatchers.IO)
        {
            citieslist.value = retrofit.getcities()
        }
        return citieslist.value
    }

}

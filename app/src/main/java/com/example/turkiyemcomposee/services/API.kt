package com.example.turkiyemcomposee.services

import com.example.turkiyemcomposee.models.City
import retrofit2.http.GET

interface API {
    @GET("api/cities")
    suspend fun getcities(): List<City>

}
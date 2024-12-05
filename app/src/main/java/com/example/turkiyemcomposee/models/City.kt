package com.example.turkiyemcomposee.models

import kotlinx.serialization.Serializable

@Serializable
data class City(
    val id: Int,
    val name: String,
    val population: String?,
    val description: String?,
    val images: String?,
    val latitude: Double?,
    val longitude: Double?)

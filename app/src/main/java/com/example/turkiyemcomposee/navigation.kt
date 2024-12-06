package com.example.turkiyemcomposee

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.turkiyemcomposee.models.City
import com.example.turkiyemcomposee.screens.citydetailscreen
import com.example.turkiyemcomposee.screens.citysscreen

import com.example.turkiyemcomposee.screens.loginscreen
import com.example.turkiyemcomposee.screens.register
import com.example.turkiyemcomposee.viewmodel.viewmodel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.json.Json

@Composable
fun navigate(viewmodel: viewmodel,auth: FirebaseAuth)
{
    val navController  = rememberNavController()

    NavHost(navController = navController, startDestination = "Register")
    {
        composable("Login"){
            loginscreen(navController,auth)
        }

        composable("Register"){
            register(navController,auth)
        }

        composable("Citys") {
            citysscreen(navController,viewmodel.getdata())
        }

        composable(
            "cityDetails/{city}",
            arguments = listOf(navArgument("city") { type = NavType.StringType })
        ) { backStackEntry ->
            val cityJson = backStackEntry.arguments?.getString("city")
            val city = cityJson?.let { Json.decodeFromString<City>(it) }
            citydetailscreen(city)
        }

    }
}
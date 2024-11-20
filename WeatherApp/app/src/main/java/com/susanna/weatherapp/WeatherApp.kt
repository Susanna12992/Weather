package com.susanna.weatherapp


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.susanna.weatherapp.presentation.navigation.WeatherNavHost

@Composable
fun WeatherApp(navController: NavHostController = rememberNavController()) {
    WeatherNavHost(navController = navController)
}
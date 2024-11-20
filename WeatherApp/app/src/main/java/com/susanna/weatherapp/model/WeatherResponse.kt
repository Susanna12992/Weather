package com.susanna.weatherapp.model

import com.susanna.weatherapp.model.Main
import com.susanna.weatherapp.model.Weather

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String
)
package com.susanna.weatherapp.presentation.home

import com.susanna.weatherapp.model.WeatherData
import com.susanna.weatherapp.utils.UiText

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isValidSearch: Boolean = false,
    val searchedText: String = "",
    val weatherInfo: WeatherData? = null,
    val needRetryScreen: Boolean = true,
    val error: UiText? = null
)
package com.susanna.weatherapp.domain.repo

import com.susanna.weatherapp.model.GeoResponse
import com.susanna.weatherapp.model.WeatherResponse
import retrofit2.Response

interface WeatherRepository {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Response<WeatherResponse>

    suspend fun getCity(
        city: String
    ): Response<List<GeoResponse>>
}
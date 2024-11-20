package com.susanna.weatherapp.domain.repo

import com.susanna.weatherapp.network.WeatherApi
import com.susanna.weatherapp.model.GeoResponse
import com.susanna.weatherapp.model.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Response<WeatherResponse> {
        return weatherApi.getWeather(latitude, longitude)
    }

    override suspend fun getCity(city: String): Response<List<GeoResponse>> {
        return weatherApi.searchCity(city)
    }

}
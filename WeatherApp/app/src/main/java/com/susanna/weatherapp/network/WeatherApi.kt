package com.susanna.weatherapp.network

import com.susanna.weatherapp.utils.Constants
import com.susanna.weatherapp.model.GeoResponse
import com.susanna.weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = Constants.API_KEY
    ): Response<WeatherResponse>

    @GET("geo/1.0/direct")
    suspend fun searchCity(
        @Query("q") query: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") apiKey: String = Constants.API_KEY
    ): Response<List<GeoResponse>>
}

package com.susanna.weatherapp.domain.mapper

import com.susanna.weatherapp.common.Mapper
import com.susanna.weatherapp.model.WeatherData
import com.susanna.weatherapp.model.WeatherResponse
import javax.inject.Inject

class WeatherResponseToModel @Inject constructor(): Mapper<WeatherResponse?, WeatherData> {

    override fun mapFrom(
        from: WeatherResponse?
    ): WeatherData {
            return  WeatherData(
                name = from?.name ?: "",
                temp = ((from?.main?.temp?.minus(273.15f))?.times(9/5 )?.plus(32)) ?: 0.0f,
                icon = from?.weather?.get(0)?.icon ?: "",
                temp_max = ((from?.main?.temp_max?.minus(273.15f))?.times(9/5 )?.plus(32))?: 0.0f,
                temp_min = ((from?.main?.temp_min?.minus(273.15f))?.times(9/5 )?.plus(32)) ?: 0.0f,
                description = from?.weather?.get(0)?.description ?: ""
            )
    }
}

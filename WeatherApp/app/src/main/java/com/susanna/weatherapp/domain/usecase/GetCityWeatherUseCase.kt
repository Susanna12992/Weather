package com.susanna.weatherapp.domain.usecase

import com.susanna.weatherapp.network.Result
import com.susanna.weatherapp.network.safeApiCall
import com.susanna.weatherapp.model.WeatherData
import com.susanna.weatherapp.domain.mapper.WeatherResponseToModel
import com.susanna.weatherapp.utils.DataError
import com.susanna.weatherapp.utils.Error
import com.susanna.weatherapp.domain.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val mapper: WeatherResponseToModel
) {
    operator fun invoke(city: String
    ): Flow<Result<WeatherData, Error>> = flow {
        val result = safeApiCall {
            repository.getCity(city)
        }
        when (result) {
            is Result.Success -> {
                if(result.data.isEmpty())
                    emit(Result.Error(DataError.CustomError("No City Found")))
                else {
                    val finalResult = safeApiCall {
                        repository.getWeather(
                            result.data[0].lat,
                            result.data[0].lon
                        )
                    }
                    when (finalResult) {
                        is Result.Success -> emit(Result.Success(mapper.mapFrom(finalResult.data)))
                        is Result.Error -> emit(Result.Error(finalResult.error))
                    }
                }
            }
            is Result.Error -> emit(Result.Error(result.error))
        }
    }
}
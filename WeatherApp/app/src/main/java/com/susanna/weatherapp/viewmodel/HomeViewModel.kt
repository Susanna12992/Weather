package com.susanna.weatherapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.susanna.weatherapp.utils.Constants.DEFAULT_CITY
import com.susanna.weatherapp.network.Result
import com.susanna.weatherapp.utils.DataError
import com.susanna.weatherapp.domain.usecase.GetCityWeatherUseCase
import com.susanna.weatherapp.presentation.home.HomeScreenState
import com.susanna.weatherapp.utils.Constants.MY_SHARED_PREFERENCES
import com.susanna.weatherapp.utils.Constants.SAVED_CITY_KEY
import com.susanna.weatherapp.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetCityWeatherUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    // state of weather api
    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState: StateFlow<HomeScreenState> get() = _homeScreenState
    val sharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCES,Context.MODE_PRIVATE)

    init {
        fetchWeeklyData(sharedPreferences.getString(SAVED_CITY_KEY, DEFAULT_CITY)?: DEFAULT_CITY)
    }

    private fun fetchWeeklyData(city: String) {
        _homeScreenState.value = _homeScreenState.value.copy(isLoading = true, error = null)

        useCase(city)
            .onEach { result ->
                _homeScreenState.value = when (result) {
                    is Result.Error -> {
                        _homeScreenState.value.copy(
                            isLoading = false,
                            error = (result.error as DataError).asUiText(),
                        )
                    }

                    is Result.Success -> {
                        with(sharedPreferences.edit()) {
                            putString(SAVED_CITY_KEY, city)
                            apply()
                        }
                        _homeScreenState.value.copy(
                            isLoading = false,
                            weatherInfo = result.data,
                            error = null,
                            needRetryScreen = false,
                            searchedText = city
                        )
                    }
                }

            }.launchIn(viewModelScope)
    }

    fun searchCityAndGetWeather(city: String) {
        fetchWeeklyData(city)
    }

    fun onTextChanged(newText: String) {
        _homeScreenState.value =
            _homeScreenState.value.copy(isValidSearch = true)
    }

    fun onRetry() {
        fetchWeeklyData(sharedPreferences.getString(SAVED_CITY_KEY, DEFAULT_CITY)?: DEFAULT_CITY)
    }
}

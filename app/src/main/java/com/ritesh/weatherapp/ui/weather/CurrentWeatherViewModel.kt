package com.ritesh.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesh.weatherapp.data.api.ApiResponseRepo
import kotlinx.coroutines.launch

class CurrentWeatherViewModel : ViewModel() {
    private val apiResponse = ApiResponseRepo()

    val currentWeatherData = apiResponse.weatherResponseSuccess
    val currentWeatherFailure = apiResponse.apiFailed

    fun getCurrentWeather(lat: Double, lon: Double, units: String) {
        viewModelScope.launch {
            apiResponse.getCurrentWeather(lat, lon, units)
        }
    }
}
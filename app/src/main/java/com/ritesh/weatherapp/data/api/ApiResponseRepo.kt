package com.ritesh.weatherapp.data.api

import androidx.lifecycle.MutableLiveData
import com.ritesh.weatherapp.data.model.forecast.ForecastResponse
import com.ritesh.weatherapp.data.model.weather.WeatherResponse
import com.ritesh.weatherapp.utils.Constants
import java.lang.Exception

class ApiResponseRepo {

    private val apiService = RetrofitManager.apiService

    val apiFailed = MutableLiveData<String>()

    val weatherResponseSuccess = MutableLiveData<WeatherResponse>()
    val forecastResponseSuccess = MutableLiveData<ForecastResponse>()

    suspend fun getCurrentWeather(lat: Double, lon: Double, units: String) {
        try {
            val weatherResponse = apiService.getCurrentWeather(lat, lon, Constants.APP_ID, units)

            if (weatherResponse.isSuccessful && weatherResponse.body()?.cod == Constants.SUCCESS_STATUS_CODE) {
                weatherResponseSuccess.postValue(weatherResponse.body())
            } else {
                apiFailed.postValue(weatherResponse.message())
            }
        } catch (exception: Exception) {
            apiFailed.postValue(exception.message)
        }
    }

    suspend fun getWeatherForecast(lat: Double, lon: Double, units: String) {
        try {
            val forecastResponse = apiService.getWeatherForecast(lat, lon, Constants.APP_ID, units)

            if (forecastResponse.isSuccessful && forecastResponse.body()?.cod == Constants.SUCCESS_STATUS_CODE) {
                forecastResponseSuccess.postValue(forecastResponse.body())
            } else {
                apiFailed.postValue(forecastResponse.message())
            }
        } catch (exception: Exception) {
            apiFailed.postValue(exception.message)
        }
    }
}
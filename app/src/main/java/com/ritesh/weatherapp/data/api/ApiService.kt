package com.ritesh.weatherapp.data.api

import com.ritesh.weatherapp.data.model.forecast.ForecastResponse
import com.ritesh.weatherapp.data.model.weather.WeatherResponse
import com.ritesh.weatherapp.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(Constants.WEATHER)
    suspend fun getCurrentWeather(@Query ("lat") lat: Double,
                                  @Query ("lon") lon: Double,
                                  @Query ("appid") appId: String,
                                  @Query ("units") units: String) : Response<WeatherResponse>

    @GET(Constants.FORECAST)
    suspend fun getWeatherForecast(@Query ("lat") lat: Double,
                                  @Query ("lon") lon: Double,
                                  @Query ("appid") appId: String,
                                  @Query ("units") units: String) : Response<ForecastResponse>

}
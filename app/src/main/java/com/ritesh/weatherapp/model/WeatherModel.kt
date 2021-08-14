package com.ritesh.weatherapp.model

data class WeatherModel(
    val weatherIcon: String,
    val weatherCity: String,
    val weatherDescription: String,
    val currentTemperature: String,
    val highTemp: String,
    val lowTemp: String,
    val humidity: String,
    val wind: String,
    val sunrise: String,
    val sunset: String,
    val lat: Double,
    val lon : Double
)

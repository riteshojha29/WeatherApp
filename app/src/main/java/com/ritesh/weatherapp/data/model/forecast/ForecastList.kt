package com.ritesh.weatherapp.data.model.forecast

import com.ritesh.weatherapp.data.model.weather.WeatherSys
import com.google.gson.annotations.SerializedName
import com.ritesh.weatherapp.data.model.common.Clouds
import com.ritesh.weatherapp.data.model.common.Weather
import com.ritesh.weatherapp.data.model.common.Wind

data class ForecastList (
    @SerializedName("dt") val dt : Int,
    @SerializedName("main") val forecastMain : ForecastMain,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("clouds") val clouds : Clouds,
    @SerializedName("wind") val wind : Wind,
    @SerializedName("visibility") val visibility : Int,
    @SerializedName("pop") val pop : Int,
    @SerializedName("sys") val sys : WeatherSys,
    @SerializedName("dt_txt") val dt_txt : String
)
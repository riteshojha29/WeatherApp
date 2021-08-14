package com.ritesh.weatherapp.data.model.weather

import com.ritesh.weatherapp.data.model.forecast.ForecastMain
import com.ritesh.weatherapp.data.model.common.Weather
import com.ritesh.weatherapp.data.model.common.Wind
import com.google.gson.annotations.SerializedName
import com.ritesh.weatherapp.data.model.common.Clouds
import com.ritesh.weatherapp.data.model.common.Coord

data class WeatherResponse(
    @SerializedName("coord") val coord : Coord,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("base") val base : String,
    @SerializedName("main") val main : ForecastMain,
    @SerializedName("visibility") val visibility : Int,
    @SerializedName("wind") val wind : Wind,
    @SerializedName("clouds") val clouds : Clouds,
    @SerializedName("dt") val dt : Int,
    @SerializedName("sys") val weatherSys : WeatherSys,
    @SerializedName("timezone") val timezone : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("cod") val cod : Int
)

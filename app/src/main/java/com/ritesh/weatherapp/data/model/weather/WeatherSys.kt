package com.ritesh.weatherapp.data.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherSys (
	@SerializedName("country") val country : String,
	@SerializedName("sunrise") val sunrise : Long,
	@SerializedName("sunset") val sunset : Long
)
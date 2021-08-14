package com.ritesh.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("cod") val cod : Int,
    @SerializedName("message") val message : Int,
    @SerializedName("cnt") val cnt : Int,
    @SerializedName("list") val list : List<ForecastList>,
    @SerializedName("city") val forecastCity : ForecastCity
)

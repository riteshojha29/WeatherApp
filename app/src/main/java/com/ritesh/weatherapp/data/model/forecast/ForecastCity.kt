package com.ritesh.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName
import com.ritesh.weatherapp.data.model.common.Coord

data class ForecastCity (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("coord") val coord : Coord,
    @SerializedName("country") val country : String,
    @SerializedName("population") val population : Int,
    @SerializedName("timezone") val timezone : Int,
    @SerializedName("sunrise") val sunrise : Int,
    @SerializedName("sunset") val sunset : Int
)
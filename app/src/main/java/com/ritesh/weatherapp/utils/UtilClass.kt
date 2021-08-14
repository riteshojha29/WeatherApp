package com.ritesh.weatherapp.utils

import android.annotation.SuppressLint
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat

object UtilClass {

    fun getIconUrl(iconCode: String) =
        "${Constants.WEATHER_ICON_URL}${iconCode}${Constants.WEATHER_ICON_FORMAT}"

    @SuppressLint("SimpleDateFormat")
    fun getTimeAMPM(epochTime: Long): String {
        val dateFormat = SimpleDateFormat("hh.mm aa")
        return dateFormat.format(epochTime)
    }

    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }

}
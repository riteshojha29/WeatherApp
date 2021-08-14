package com.ritesh.weatherapp.ui.weather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ritesh.weatherapp.R
import com.ritesh.weatherapp.data.model.weather.WeatherResponse
import com.ritesh.weatherapp.model.WeatherModel
import com.ritesh.weatherapp.ui.MainActivity
import com.ritesh.weatherapp.ui.SharedViewModel
import com.ritesh.weatherapp.utils.Constants
import com.ritesh.weatherapp.utils.UtilClass.getIconUrl
import com.ritesh.weatherapp.utils.UtilClass.getTimeAMPM
import com.ritesh.weatherapp.utils.UtilClass.roundOffDecimal

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel
    private lateinit var sharedViewModel: SharedViewModel

    lateinit var itemImage : ImageView
    lateinit var itemCityName: TextView
    lateinit var itemDescription : TextView
    lateinit var itemTemperature: TextView
    lateinit var itemHighTempValue: TextView
    lateinit var itemHumidityValue: TextView
    lateinit var itemSunriseValue: TextView
    lateinit var itemLowTempValue: TextView
    lateinit var itemWindValue: TextView
    lateinit var itemSunsetValue: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.current_weather_fragment, container, false)

        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        initViews(view)

        val context = activity as MainActivity
        currentWeatherObserver(context)

        sharedViewModel.weatherLocation.observe(viewLifecycleOwner, Observer {
            viewModel.getCurrentWeather(it.lat, it.lon, Constants.UNIT_METRIC)
        })
    }

    private fun initViews(view: View) {
        itemImage = view.findViewById(R.id.itemImage)
        itemCityName = view.findViewById(R.id.itemCityName)
        itemDescription = view.findViewById(R.id.itemDescription)
        itemTemperature = view.findViewById(R.id.itemTemperature)
        itemHighTempValue = view.findViewById(R.id.itemHighTempValue)
        itemHumidityValue = view.findViewById(R.id.itemHumidityValue)
        itemSunriseValue = view.findViewById(R.id.itemSunriseValue)
        itemLowTempValue = view.findViewById(R.id.itemLowTempValue)
        itemWindValue = view.findViewById(R.id.itemWindValue)
        itemSunsetValue = view.findViewById(R.id.itemSunsetValue)
    }

    private fun currentWeatherObserver(context: Context) {
        viewModel.currentWeatherData.observe(viewLifecycleOwner, Observer { weatherResponse ->
            populateWeatherData(wrapWeatherData(weatherResponse))
        })

        viewModel.currentWeatherFailure.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })

    }

    private fun wrapWeatherData(response: WeatherResponse) : WeatherModel {
        return WeatherModel(
            weatherIcon = getIconUrl(response.weather[0].icon),
            weatherCity = response.name,
            weatherDescription = response.weather[0].description,
            currentTemperature = "${response.main.temp}${Constants.UNICODE_DEGREE}",
            highTemp = "${response.main.temp_max}${Constants.UNICODE_DEGREE}",
            lowTemp = "${response.main.temp_min}${Constants.UNICODE_DEGREE}",
            humidity = "${response.main.humidity} %",
            wind = "${roundOffDecimal(response.wind.speed * 3.6)} km/h",
            sunrise = getTimeAMPM(response.weatherSys.sunrise),
            sunset = getTimeAMPM(response.weatherSys.sunset),
            lat = response.coord.lat,
            lon = response.coord.lon
        )
    }

    private fun populateWeatherData(weatherData: WeatherModel) {
        Glide.with(requireContext())
            .load(weatherData.weatherIcon)
            .into(itemImage)

        itemCityName.text = weatherData.weatherCity
        itemDescription.text = weatherData.weatherDescription
        itemTemperature.text = weatherData.currentTemperature
        itemHighTempValue.text = weatherData.highTemp
        itemHumidityValue.text = weatherData.humidity
        itemSunriseValue.text = weatherData.sunrise
        itemLowTempValue.text = weatherData.lowTemp
        itemWindValue.text = weatherData.wind
        itemSunsetValue.text = weatherData.sunset
    }

}
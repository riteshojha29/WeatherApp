package com.ritesh.weatherapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ritesh.weatherapp.model.LocationModel

class SharedViewModel: ViewModel() {
    val weatherLocation = MutableLiveData<LocationModel>()
    val newLocationData = MutableLiveData<LocationModel>()
}
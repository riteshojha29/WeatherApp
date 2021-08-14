package com.ritesh.weatherapp.ui.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesh.weatherapp.data.dao.DatabaseBuilder
import com.ritesh.weatherapp.data.dao.DatabaseHelper
import com.ritesh.weatherapp.data.dao.DatabaseHelperImpl
import com.ritesh.weatherapp.data.dao.Location
import com.ritesh.weatherapp.model.LocationModel
import com.ritesh.weatherapp.ui.App
import kotlinx.coroutines.launch

class DashboardViewModel: ViewModel() {
    val locationInsertSuccess = MutableLiveData<Boolean>()
    val locationDeleteSuccess = MutableLiveData<Boolean>()
    val locationData = MutableLiveData<List<LocationModel>>()
    val locationDataFailure = MutableLiveData<String>()

    fun fetchLocations(dbHelper: DatabaseHelperImpl) {
        viewModelScope.launch {
            try {
                val data = dbHelper.getAllLocations()

                if(data.isNullOrEmpty()) {
                    locationDataFailure.postValue("No City added")
                } else {
                    val list = ArrayList<LocationModel>()
                    data.forEach {
                        list.add(
                            LocationModel(
                                it.locality!!,
                                it.lat,
                                it.lon
                            )
                        )
                    }
                    locationData.postValue(list)
                }
            } catch (exception: Exception) {
                locationDataFailure.postValue(exception.message)
            }
        }
    }

    fun insertLocation(dbHelper: DatabaseHelperImpl, location: LocationModel) {
        viewModelScope.launch {
            try {
                val newLocationData = Location(
                    locality = location.locality,
                    lat = location.lat,
                    lon = location.lon
                )

                dbHelper.insertLocation(newLocationData)

                locationInsertSuccess.postValue(true)
            } catch (exception: Exception) {
                locationDataFailure.postValue(exception.message)
            }
        }
    }

    fun deleteLocation(dbHelper: DatabaseHelperImpl, location: LocationModel) {
        viewModelScope.launch {
            try {
                val locationData = Location(
                    locality = location.locality,
                    lat = location.lat,
                    lon = location.lon
                )

                dbHelper.deleteLocation(locationData)

                locationDeleteSuccess.postValue(true)
            } catch (exception: Exception) {
                locationDataFailure.postValue(exception.message)
            }
        }
    }
}
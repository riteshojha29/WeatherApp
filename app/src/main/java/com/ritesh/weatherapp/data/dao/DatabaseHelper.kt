package com.ritesh.weatherapp.data.dao

interface DatabaseHelper {

    suspend fun getAllLocations(): List<Location>

    suspend fun insertLocation(location: Location)

    suspend fun deleteLocation(location: Location)
}
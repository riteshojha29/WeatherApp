package com.ritesh.weatherapp.data.dao

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getAllLocations(): List<Location> = appDatabase.locationDAO().getAllLocations()

    override suspend fun insertLocation(location: Location) = appDatabase.locationDAO().insertLocation(location)

    override suspend fun deleteLocation(location: Location) = appDatabase.locationDAO().deleteLocation(location)

}
package com.ritesh.weatherapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocationDAO {

    @Query("SELECT * FROM location")
    suspend fun getAllLocations(): List<Location>

    @Insert
    suspend fun insertLocation(location: Location)

    @Delete
    suspend fun deleteLocation(location: Location)
}
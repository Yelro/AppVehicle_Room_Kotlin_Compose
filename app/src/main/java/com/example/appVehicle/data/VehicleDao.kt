package com.example.appVehicle.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Query("SELECT * from Vehicles ORDER BY make ASC")
    fun getAllVehicles(): Flow<List<Vehicle>>

    @Query("SELECT * from Vehicles WHERE id = :id")
    fun getVehicle(id: Int): Flow<Vehicle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(Vehicles: List<Vehicle>)
}

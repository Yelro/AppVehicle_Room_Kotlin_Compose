package com.example.appVehicle.data

import kotlinx.coroutines.flow.Flow

interface VehiclesRepository {

    fun getAllVehiclesStream(): Flow<List<Vehicle>>

    fun getVehicleStream(id: Int): Flow<Vehicle?>

    suspend fun insertVehicles(Vehicles: List<Vehicle>)
}

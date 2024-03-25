package com.example.appVehicle.data

import android.content.Context

interface VehicleContainer {
    val VehiclesRepository: VehiclesRepository
}

class AppDataContainer(private val context: Context) : VehicleContainer {

    override val VehiclesRepository: VehiclesRepository by lazy {
        VehiclesRepositoryOffline(VehicleDatabase.getDatabase(context).VehicleDao())
    }
}

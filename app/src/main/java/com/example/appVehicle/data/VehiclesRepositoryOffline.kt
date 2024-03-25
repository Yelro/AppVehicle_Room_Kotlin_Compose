package com.example.appVehicle.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VehiclesRepositoryOffline(private val VehicleDao: VehicleDao) : VehiclesRepository {
    override fun getAllVehiclesStream(): Flow<List<Vehicle>> {
        return VehicleDao.getAllVehicles().map { Vehicles ->
            if (Vehicles.isEmpty()) {
                listOf(
                    Vehicle(make = "BYD", model = "Tang", price = 45900.0, autonomy = "500 km", power = "517 hp", batterytype = "Litio-ion", chargingtime = "10 hours (level 2)", imageString = "https://i.stack.imgur.com/pXEqr.png")
                )
            } else {
                Vehicles
            }
        }
    }

    override fun getVehicleStream(id: Int): Flow<Vehicle?> = VehicleDao.getVehicle(id)

    override suspend fun insertVehicles(Vehicles: List<Vehicle>) {
        VehicleDao.insertAll(Vehicles)
    }
}

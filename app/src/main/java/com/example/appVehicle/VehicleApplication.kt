package com.example.appVehicle

import android.app.Application
import com.example.appVehicle.data.VehicleContainer
import com.example.appVehicle.data.AppDataContainer
import com.example.appVehicle.data.Vehicle
import kotlinx.coroutines.runBlocking

class VehicleApplication : Application() {

    lateinit var container: VehicleContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)

        val defaultVehicles = listOf(
            Vehicle(make = "BYD", model = "Tang", price = 45900.0, autonomy = "500 km", power = "517 hp", batterytype = "Litio-ion", chargingtime = "10 hours (level 2)", imageString = "https://i.stack.imgur.com/pXEqr.png"),
            Vehicle(make = "Chevrolet", model = "Bolt EV", price = 34990.0, autonomy = "420 km", power = "200 hp", batterytype = "Litio-ion", chargingtime = "8 hours (level 2)", imageString = "https://i.stack.imgur.com/4eoZx.png"),
            Vehicle(make = "Chevrolet", model = "Bolt EUV", price = 39990.0, autonomy = "420 km", power = "200 hp", batterytype = "Litio-ion", chargingtime = "8 hours (level 2)", imageString = "https://i.stack.imgur.com/65NUM.png"),
            Vehicle(make = "Great Wall Motors", model = "Tang", price = 33990.0, autonomy = "400 km", power = "171 hp", batterytype = "Litio-ion", chargingtime = "8 hours (level 2)", imageString = "https://i.stack.imgur.com/YoBZf.png"),
            Vehicle(make = "MG Motor", model = "ZS EV", price = 32990.0, autonomy = "380 km", power = "143 hp", batterytype = "Litio-ion", chargingtime = "7 hours (level 2)", imageString = "https://i.stack.imgur.com/a03WB.png"),
            Vehicle(make = "Hyundai", model = "Kona Electric", price = 42990.0, autonomy = "484 km", power = "204 hp", batterytype = "Litio-ion", chargingtime = "7.5 hours (level 2)", imageString = "https://i.stack.imgur.com/llXWv.png"),
            Vehicle(make = "Kia", model = "Niro EV", price = 43990.0, autonomy = "460 km", power = "201 hp", batterytype = "Litio-ion", chargingtime = "7.5 hours (level 2)", imageString = "https://i.stack.imgur.com/pmVEa.png"),
            Vehicle(make = "Nissan", model = "LEAF", price = 37990.0, autonomy = "389 km", power = "147 hp", batterytype = "Litio-ion", chargingtime = "7.5 hours (level 2)", imageString = "https://i.stack.imgur.com/9lUjF.png"),
            Vehicle(make = "Renault", model = "Zoe", price = 35990.0, autonomy = "385 km", power = "135 hp", batterytype = "Litio-ion", chargingtime = "9 hours (level 2)", imageString = "https://i.stack.imgur.com/gKa4I.png")
        )

        runBlocking {

            container.VehiclesRepository.insertVehicles(defaultVehicles)
        }
    }
}


package com.example.appVehicle.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Vehicles")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var make: String,
    var model: String,
    var price: Double,
    var autonomy: String,
    var power: String,
    var batterytype: String,
    var chargingtime: String,
    var imageString: String
)

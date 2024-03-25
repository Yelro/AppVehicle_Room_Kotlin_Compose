package com.example.appVehicle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Vehicle::class], version = 1, exportSchema = false)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun VehicleDao(): VehicleDao

    companion object {
        @Volatile
        private var Instance: VehicleDatabase? = null

        fun getDatabase(context: Context): VehicleDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, VehicleDatabase::class.java, "Vehicle_database")

                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
    suspend fun insertVehicles(Vehicles: List<Vehicle>) {
        VehicleDao().insertAll(Vehicles)
    }
}

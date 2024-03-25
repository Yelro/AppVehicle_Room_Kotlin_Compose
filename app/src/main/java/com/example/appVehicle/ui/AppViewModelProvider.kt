package com.example.appVehicle.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appVehicle.VehicleApplication
import com.example.appVehicle.ui.Vehicle.VehicleDetailsViewModel
import com.example.appVehicle.ui.home.HomeViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            VehicleDetailsViewModel(
                this.createSavedStateHandle(),
                VehicleApplication().container.VehiclesRepository
            )
        }

        initializer {
            HomeViewModel(VehicleApplication().container.VehiclesRepository)
        }
    }
}

fun CreationExtras.VehicleApplication(): VehicleApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VehicleApplication)
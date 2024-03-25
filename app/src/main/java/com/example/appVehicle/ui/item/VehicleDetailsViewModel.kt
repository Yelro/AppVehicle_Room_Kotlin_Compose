package com.example.appVehicle.ui.Vehicle

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appVehicle.data.Vehicle
import com.example.appVehicle.data.VehiclesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class VehicleDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val VehiclesRepository: VehiclesRepository,
) : ViewModel() {

    private val VehicleId: Int = checkNotNull(savedStateHandle[VehicleDetailsScreen.VehicleIdArg])

    val uiState: StateFlow<VehicleDetailsUiState> =
        VehiclesRepository.getVehicleStream(VehicleId)
            .filterNotNull()
            .map {
                VehicleDetailsUiState(outOfStock = it.price <= 0, VehicleDetails = it.toVehicleDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = VehicleDetailsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class VehicleDetailsUiState(
    val outOfStock: Boolean = true,
    val VehicleDetails: VehicleDetails = VehicleDetails()
)

data class VehicleDetails(

    var id: Int = 0,
    var make: String="",
    var model: String="",
    val price : Double=0.0,
    var autonomy: String="",
    var power: String="",
    var batterytype: String="",
    var chargingtime: String="",
    var imageString: String=""

)

fun VehicleDetails.toVehicle(): Vehicle {
    return Vehicle(
        id = id,
        make = make,
        model = model,
        price = price,
        autonomy = autonomy,
        power = power,
        batterytype = batterytype,
        chargingtime = chargingtime,
        imageString = imageString
    )
}

fun Vehicle.toVehicleDetails(): VehicleDetails = VehicleDetails(
    id = id,
    make = make,
    model = model,
    price = price,
    autonomy = autonomy,
    power = power,
    batterytype = batterytype,
    chargingtime = chargingtime,
    imageString = imageString
)

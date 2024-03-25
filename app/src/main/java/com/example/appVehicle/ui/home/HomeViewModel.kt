package com.example.appVehicle.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appVehicle.data.Vehicle
import com.example.appVehicle.data.VehiclesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(VehiclesRepository: VehiclesRepository) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        VehiclesRepository.getAllVehiclesStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class HomeUiState(val VehicleList: List<Vehicle> = listOf())

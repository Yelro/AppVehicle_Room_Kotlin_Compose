package com.example.appVehicle.ui.Vehicle

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.appVehicle.R
import com.example.appVehicle.VehicleTopAppBar
import com.example.appVehicle.data.Vehicle
import com.example.appVehicle.ui.ViewModelProvider
import com.example.appVehicle.ui.navigation.NavigationVehicle
import java.text.NumberFormat

object VehicleDetailsScreen : NavigationVehicle {
    override val route = "Vehicle_details"
    override val headerT = R.string.Vehicle_header
    const val VehicleIdArg = "VehicleId"
    val routeWithArgs = "$route/{$VehicleIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VehicleDetailsViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            VehicleTopAppBar(
                title = stringResource(VehicleDetailsScreen.headerT),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        VehicleDetailsBody(
            VehicleDetailsUiState = uiState.value,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun VehicleDetailsBody(
    VehicleDetailsUiState: VehicleDetailsUiState,

    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        VehicleDetails(
            Vehicle =     VehicleDetailsUiState.VehicleDetails.toVehicle(),
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@Composable
fun VehicleDetails(
    Vehicle: Vehicle, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            //llamar imagen
            Viewimage(Vehicle.imageString)

            VehicleDetailsRow(
                labelResID = R.string.Vehicle_of_make,
                VehicleDetail = Vehicle.make,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            VehicleDetailsRow(
                labelResID = R.string.Vehicle_of_model,
                VehicleDetail = Vehicle.model,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            VehicleDetailsRow(
                labelResID = R.string.Vehicle_of_price,
                VehicleDetail = Vehicle.price.toString(),
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            VehicleDetailsRow(
                labelResID = R.string.Vehicle_of_autonomy,
                VehicleDetail = Vehicle.autonomy,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            VehicleDetailsRow(
                labelResID = R.string.Vehicle_of_power,
                VehicleDetail = Vehicle.power,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium)
                )
            )
            VehicleDetailsRow(
                labelResID = R.string.Vehicle_of_batterytype,
                VehicleDetail = Vehicle.batterytype,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium)
                )
            )
            VehicleDetailsRow(
                labelResID = R.string.Vehicle_of_chargingtime,
                VehicleDetail = Vehicle.chargingtime,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium)
                )
            )
        }


    }
}

@Composable
private fun VehicleDetailsRow(
    @StringRes labelResID: Int, VehicleDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = VehicleDetail, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun Viewimage(logo: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth() //
            .height(IntrinsicSize.Min)
            .aspectRatio(1f)
    ) {
        // UCArgar imagen
        Image(
            painter = rememberImagePainter(logo),
            contentDescription = "Image",
            modifier = Modifier.fillMaxSize()
        )
    }
}

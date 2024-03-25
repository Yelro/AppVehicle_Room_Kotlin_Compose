package com.example.appVehicle.ui.home
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.appVehicle.R
import com.example.appVehicle.VehicleTopAppBar
import com.example.appVehicle.data.Vehicle
import com.example.appVehicle.ui.ViewModelProvider
import com.example.appVehicle.ui.navigation.NavigationVehicle
import com.example.appVehicle.ui.theme.Purple40
import com.example.appVehicle.ui.theme.YelroMotorTheme

object HomeScreen : NavigationVehicle {
    override val route = "home"
    override val headerT = R.string.Vehicle_header
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToVehicleUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            VehicleTopAppBar(
                title = stringResource(HomeScreen.headerT),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        HomeBody(
            VehicleList = homeUiState.VehicleList,
            onVehicleClick = navigateToVehicleUpdate,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun HomeBody(
    VehicleList: List<Vehicle>, onVehicleClick: (Int) -> Unit, modifier: Modifier = Modifier
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (VehicleList.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp),
                color = Purple40
            )
        } else {
            AppVehicleList(
                VehicleList = VehicleList,
                onVehicleClick = { onVehicleClick(it.id) },
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun AppVehicleList(
    VehicleList: List<Vehicle>, onVehicleClick: (Vehicle) -> Unit, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = VehicleList, key = { it.id }) { Vehicle ->
            AppVehicleVehicle(Vehicle = Vehicle,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onVehicleClick(Vehicle) })
        }
    }
}

@Composable
private fun AppVehicleVehicle(
    Vehicle: Vehicle, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    )
    {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_large))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.padding_small)
                )
            ) {
                Text(
                    text = Vehicle.make,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = Vehicle.model,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.width(16.dp)) // Espacio entre los atributos y la imagen
            //cargar imagen
            Image(
                painter = rememberImagePainter(Vehicle.imageString),
                contentDescription = null, // Puedes añadir una descripción
                modifier = Modifier.size(180.dp) // Modifica el tamaño
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    YelroMotorTheme {
        HomeBody(listOf( ), onVehicleClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    YelroMotorTheme {
        HomeBody(listOf(), onVehicleClick = {})
    }
}


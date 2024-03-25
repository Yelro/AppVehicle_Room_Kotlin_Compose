package com.example.appVehicle.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appVehicle.ui.Vehicle.VehicleDetailsScreen
import com.example.appVehicle.ui.home.HomeScreen
@Composable
fun VehicleNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route,
        modifier = modifier
    ) {
        composable(route = HomeScreen.route) {
            HomeScreen(
                navigateToVehicleUpdate = {
                    navController.navigate("${VehicleDetailsScreen.route}/${it}")
                }
            )
        }
        composable(
            route = VehicleDetailsScreen.routeWithArgs,
            arguments = listOf(navArgument(VehicleDetailsScreen.VehicleIdArg) {
                type = NavType.IntType
            })
        ) {
            VehicleDetailsScreen(
                navigateBack = { navController.navigateUp() }
            )
        }

    }
}
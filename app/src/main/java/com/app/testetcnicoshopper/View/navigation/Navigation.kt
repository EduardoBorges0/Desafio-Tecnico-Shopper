package com.app.testetcnicoshopper.View.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.testetcnicoshopper.Model.Repositories.DriveRepositories.DriverRepositories
import com.app.testetcnicoshopper.Model.Repositories.TravelListRepositories.TravelListRepositories
import com.app.testetcnicoshopper.Model.Repositories.TripRepositories.TripeRepositories
import com.app.testetcnicoshopper.Model.RetroFit.ConfirmTripAPI.ConfirmTripAPI
import com.app.testetcnicoshopper.Model.RetroFit.SearchDriveAPI.DrivePOST
import com.app.testetcnicoshopper.Model.RetroFit.TravelListAPI.TraveListAPI
import com.app.testetcnicoshopper.View.avaliableDrivers.DriversAvaliableComposable
import com.app.testetcnicoshopper.View.travelHistory.TravelHistory
import com.app.testetcnicoshopper.View.mainScreen.OriginToDestin
import com.app.testetcnicoshopper.ViewModel.DriveViewModel.DriveViewModel
import com.app.testetcnicoshopper.ViewModel.TravelViewModel.TravelListViewModel
import com.app.testetcnicoshopper.ViewModel.TripViewModel.TripViewModel

class Navigation : ComponentActivity() {
    private lateinit var DriveViewModel : DriveViewModel
    private lateinit var TripViewModel : TripViewModel
    private lateinit var TravelListViewModel: TravelListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DriveViewModel = DriveViewModel(DriverRepositories(DrivePOST.getInstance()))
        TripViewModel = TripViewModel(TripeRepositories(ConfirmTripAPI.getInstance()))
        TravelListViewModel = TravelListViewModel(TravelListRepositories(TraveListAPI.getInstance()))
        enableEdgeToEdge()
        setContent {
            SetupNavigation(DriveViewModel, TripViewModel, TravelListViewModel)
        }
    }
}

@Composable
fun SetupNavigation(driveViewModel: DriveViewModel, tripViewModel: TripViewModel, travelListViewModel: TravelListViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Main"){
        composable("Main") { OriginToDestin(driveViewModel, navController) }
        composable("AvaliableDriver?userId={userId}&origin={origin}&destination={destination}", arguments = listOf(
         navArgument("userId") { type = NavType.StringType },
         navArgument("origin") { type = NavType.StringType },
         navArgument("destination") { type = NavType.StringType }
        )) {
            val userId = it.arguments?.getString("userId") ?: ""
            val origin = it.arguments?.getString("origin") ?: ""
            val destination = it.arguments?.getString("destination") ?: ""
            DriversAvaliableComposable(userId = userId, origin = origin, destination = destination, response = driveViewModel.searchResult, navHostController = navController, tripViewModel = tripViewModel) }
        composable("TravelHistory") { TravelHistory(travelListViewModel, navController) }
    }
}

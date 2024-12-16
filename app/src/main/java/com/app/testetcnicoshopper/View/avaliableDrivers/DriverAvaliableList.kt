package com.app.testetcnicoshopper.View.avaliableDrivers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.testetcnicoshopper.Model.entities.DriveEntities.RouteResponse
import com.app.testetcnicoshopper.View.components.Loading
import com.app.testetcnicoshopper.ViewModel.TripViewModel.TripViewModel
import retrofit2.Response

@Composable
fun DriversAvaliableList(responseValue: Response<RouteResponse>?, origin: String, destination: String, userId: String, tripViewModel: TripViewModel, navHostController: NavHostController) {
    if (responseValue?.body() != null) {
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
            responseValue.body()!!.options.forEachIndexed { index, _ ->
                item {
                    CardsDrivers(
                        origin = origin,
                        destination = destination,
                        userId = userId,
                        index = index,
                        response = responseValue,
                        tripViewModel = tripViewModel,
                        navHostController = navHostController
                    )
                }
            }
        }
    } else {
        Loading()
    }
}

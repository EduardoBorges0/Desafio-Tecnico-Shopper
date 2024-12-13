package com.app.testetcnicoshopper.View.avaliableDrivers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.app.testetcnicoshopper.Model.DTO.TripDTO.DriverTripDTO
import com.app.testetcnicoshopper.Model.DTO.TripDTO.TripDTO
import com.app.testetcnicoshopper.Model.entities.DriveEntities.RouteResponse
import com.app.testetcnicoshopper.R
import com.app.testetcnicoshopper.View.components.IconPopUp
import com.app.testetcnicoshopper.View.components.Loading
import com.app.testetcnicoshopper.ViewModel.TripViewModel.TripViewModel
import com.app.testetcnicoshopper.ui.theme.DarkGreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

@Composable
fun DriversAvaliableComposable(
   origin: String,
   destination: String,
   userId: String,
   response: LiveData<Response<RouteResponse>>,
   tripViewModel: TripViewModel,
   navHostController: NavHostController) {

   val responseAPITripConfirm by response.observeAsState()

   Box(modifier = Modifier.fillMaxSize()) {
      IconPopUp(navHostController = navHostController)
      Map(response,
         modifier = Modifier
            .align(Alignment.TopCenter)
      )
      Column(
         modifier = Modifier
            .clip(RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
            .background(DarkGreen)
            .padding(bottom = 30.dp)
            .fillMaxWidth()
            .height(365.dp)
            .align(Alignment.BottomCenter),
      ) {
         Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
      ) {
            DriversAvaliableList(responseValue = responseAPITripConfirm,
               origin = origin,
               destination = destination,
               userId = userId,
               tripViewModel = tripViewModel,
               navHostController = navHostController)
         }
      }
   }
}

fun confirmTrip(response : Response<RouteResponse>?,
                        index: Int,
                        tripViewModel: TripViewModel,
                        navHostController: NavHostController,
                        origin: String,
                        destination: String,
                        userId: String,
                        name: String?,
                        value: Double?){
   val body = response?.body()
   val option= body?.options?.get(index)
   val distance = body?.distance
   val duration = body?.duration
   val driverId = option?.id

   val driver = DriverTripDTO(
      id = driverId?.toInt() ?: 0,
      name = name.toString()
   )
   val trip = TripDTO(
      customer_id = userId,
      origin = origin,
      destination = destination,
      distance = distance?.toDouble() ?: 0.0,
      driver = driver,
      duration = duration.toString(),
      value = value?.toDouble() ?: 0.0
   )
   verifyClick(tripViewModel,
      navHostController = navHostController,
      tripDTO = trip
   )
}

private fun verifyClick(
   tripViewModel: TripViewModel,
   tripDTO: TripDTO,
   navHostController: NavHostController
){
   tripViewModel.verifyDriver(tripDTO)
   CoroutineScope(Dispatchers.Main).launch {
      tripViewModel.loading = true
      delay(600)
      tripViewModel.loading = false
      if (tripViewModel.verifyError) {
         tripViewModel.alertDialog = true
      } else {
         navHostController.navigate("TravelHistory")
      }
   }
}

package com.app.testetcnicoshopper.View.avaliableDrivers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.app.testetcnicoshopper.Model.entities.DriveEntities.RouteResponse
import com.app.testetcnicoshopper.R
import com.app.testetcnicoshopper.View.components.AlertDialogError
import com.app.testetcnicoshopper.ViewModel.TripViewModel.TripViewModel
import com.app.testetcnicoshopper.ui.theme.LightGreen
import retrofit2.Response

@Composable
fun CardsDrivers(index: Int,
                 origin: String,
                 destination: String,
                 userId: String,
                 response: Response<RouteResponse>?,
                 tripViewModel: TripViewModel,
                 navHostController: NavHostController
) {
    val IsNextCard = remember { mutableStateOf(false) }

    val sizeIconFalse = if(IsNextCard.value) 0.dp else 60.dp
    val sizeIconTrue = if(IsNextCard.value) 60.dp else 0.dp

    Box(
        modifier = Modifier
            .padding(top = 70.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(LightGreen)
            .width(460.dp)
            .height(260.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "arrowRight",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(50.dp))
                .size(sizeIconFalse)
                .clickable {
                    IsNextCard.value = true
                }
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "arrowLeft",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clip(RoundedCornerShape(50.dp))
                .size(sizeIconTrue)
                .clickable {
                    IsNextCard.value = false
                }
        )
        when (IsNextCard.value) {
            true -> RatingCard(response, index)
            false -> MainCard(
                userId,
                origin,
                destination,
                response,
                index,
                tripViewModel,
                navHostController
            )
        }
    }
}
@Composable
private fun MainCard(userId: String,
                     origin: String,
                     destination: String,
                     response: Response<RouteResponse>?,
                     index: Int,
                     tripViewModel: TripViewModel,
                     navHostController: NavHostController
) {
    val body = response?.body()
    val option= body?.options?.get(index)
    val name = option?.name
    val value = option?.value
    val description = option?.description
    val vehicle = option?.vehicle
    var alertDialog = tripViewModel.alertDialog

    Column {

        if (value != null) {
            NameAndValueDriver(
                name.toString(),
                value.toDouble()
            )
        }
      Row {
          Text(
              description.toString(),
              color = Color.White,
              fontSize = 14.sp,
              modifier = Modifier
                  .padding(start = 30.dp)
                  .size(140.dp)
          )
          VehicleIcon(vehicle.toString())
      }
        Spacer(modifier = Modifier.weight(1f))
        ElevatedButton(
            onClick = {
                confirmTrip(response = response,
                    index = index,
                    tripViewModel = tripViewModel,
                    navHostController = navHostController,
                    origin = origin,
                    destination = destination,
                    userId= userId,
                    name = name,
                    value= value
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(stringResource(R.string.Selecionar_motorista), color = Color.Black)
        }

    }

    if (tripViewModel.verifyError) {
        AlertDialogError(
            isDialogOpen = alertDialog,
            onDismissRequest = { alertDialog = false },
            mainMessage = tripViewModel.errorMessages.value.toString(),
            confirmAction = { alertDialog = false }
        )
    }
}

@Composable
private fun VehicleIcon(vehicle: String){
    Column {
        AsyncImage(
            model = R.drawable.caricon,
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 56.dp)
                .padding(top = 17.dp)
                .size(50.dp),

            )
        Text(text = vehicle,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 30.dp).width(120.dp))

    }
}

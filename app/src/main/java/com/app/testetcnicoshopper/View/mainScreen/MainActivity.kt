package com.app.testetcnicoshopper.View.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.app.testetcnicoshopper.R
import com.app.testetcnicoshopper.ViewModel.DriveViewModel.DriveViewModel
import com.app.testetcnicoshopper.Model.DTO.DriverDTO.DriverDTO
import com.app.testetcnicoshopper.View.components.AlertDialogError
import com.app.testetcnicoshopper.View.components.Background
import com.app.testetcnicoshopper.View.components.CustomTextField
import com.app.testetcnicoshopper.View.components.Loading
import com.app.testetcnicoshopper.ui.theme.LightGreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OriginToDestin(driveViewModel: DriveViewModel, navHostController: NavHostController){
   var loading by remember { mutableStateOf(false) }
    var originLocation by remember { mutableStateOf("") }
    var destinationLocation by remember { mutableStateOf("") }
    var customerIdLocation by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Background()

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 80.dp)
        ){
            CustomTextField(
                value = customerIdLocation,
                onValueChange = { customerIdLocation = it },
                label = stringResource(R.string.Digite_seu_id),
                enabled = !loading,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            CustomTextField(
                value = originLocation,
                onValueChange = { originLocation = it },
                label = stringResource(R.string.Onde_você_está),
                enabled = !loading,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            CustomTextField(
                value = destinationLocation,
                onValueChange = { destinationLocation = it },
                label = stringResource(R.string.Para_onde_deseja_ir),
                enabled = !loading,
                modifier = Modifier
            )
        }
        ElevatedButton (
            enabled = !loading,
            onClick = {
                val searchDriver = DriverDTO(
                    customer_id = customerIdLocation,
                    destination = destinationLocation,
                    origin = originLocation
                )
                driveViewModel.searchDriver(searchDriver)
                CoroutineScope(Dispatchers.Main).launch {
                    loading = true
                    delay(600)
                    loading = false
                    if (driveViewModel.verifyError) {
                        driveViewModel.alertDialog = true
                    } else {
                        navHostController.navigate(
                            "AvaliableDriver?userId=${searchDriver.customer_id}&origin=${searchDriver.origin}&destination=${searchDriver.destination}"
                        )
                    }
                } },

            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 120.dp)
                .width(270.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(stringResource(R.string.procurar_carro))
        }
        if(loading){
           Loading()
        }
    }
    if(driveViewModel.alertDialog) {
        AlertDialogError(
            isDialogOpen = driveViewModel.alertDialog,
            onDismissRequest = { driveViewModel.alertDialog = false },
            mainMessage = driveViewModel.errorMessage.value.toString(),
            confirmAction = { driveViewModel.alertDialog = false }
        )
    }

}


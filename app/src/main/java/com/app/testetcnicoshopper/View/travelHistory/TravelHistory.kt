package com.app.testetcnicoshopper.View.travelHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.testetcnicoshopper.R
import com.app.testetcnicoshopper.View.components.AlertDialogError
import com.app.testetcnicoshopper.View.components.Background

import com.app.testetcnicoshopper.View.components.CustomTextField
import com.app.testetcnicoshopper.View.components.IconPopUp
import com.app.testetcnicoshopper.View.travelHistory.filter.ButtonFilter
import com.app.testetcnicoshopper.View.travelHistory.filter.FilterSection
import com.app.testetcnicoshopper.ViewModel.TravelViewModel.TravelListViewModel
import com.app.testetcnicoshopper.ui.theme.DarkGreen


@Composable
fun TravelHistory(travelListViewModel: TravelListViewModel, navController: NavHostController) {
    var userId by remember { mutableStateOf("") }
    var driverId by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf(false) }
    val color = if (filter) Color.Gray.copy(alpha = 0.2f) else Color.Transparent
    val rides = travelListViewModel.responseMessage.observeAsState().value?.body()?.rides
    val filteredRides = travelListViewModel.filteredRides.observeAsState()
    val displayedRides = filteredRides.value ?: rides

    Background()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp)
            .background(color)
    ) {
        IconPopUp(navHostController = navController)

        CustomTextField(
            enabled = !filter,
            value = userId,
            onValueChange = { userId = it },
            label = stringResource(R.string.Digite_seu_id),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp)
        )

        CustomTextField(
            enabled = !filter,
            value = driverId,
            onValueChange = { driverId = it },
            label = stringResource(R.string.Digite_o_id_do_motorista),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp)
        )

        FilterSection(
            filter = filter,
            travelListViewModel = travelListViewModel,
            onFilterChange = { filter = it },
            rides = rides
        )

        ActionButtons(
            filterState = travelListViewModel.filterState.observeAsState().value,
            onFilterChange = { filter = true },
            travelListViewModel = travelListViewModel
        )

        Box(modifier = Modifier.fillMaxSize()) {

            RidesList(displayedRides)

            ButtonFilter(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp),
                text = stringResource(R.string.Selecionar_tudo),
                onClick = {
                    travelListViewModel.getTravelList(customer_id = userId, driverId = driverId)
                    userId = ""
                    driverId = ""
                },
                enabled = !filter
            )
        }

    }

    if (travelListViewModel.alertDialog) {
        AlertDialogError(
            isDialogOpen = travelListViewModel.alertDialog,
            onDismissRequest = { travelListViewModel.alertDialog = false },
            mainMessage = travelListViewModel.erroMessage.value.toString(),
            confirmAction = { travelListViewModel.alertDialog = false }
        )
    }
}
@Composable
fun ActionButtons(
    filterState: Boolean?,
    travelListViewModel: TravelListViewModel,
    onFilterChange: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (filterState == true) {
            ElevatedButton(
                onClick = { travelListViewModel.clearFilter() },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DarkGreen)
            ) {
                Text(stringResource(R.string.Remover_filtro))
            }
        }

        Icon(
            painter = if (filterState == true) painterResource(R.drawable.filter) else painterResource(R.drawable.filterfalse),
            contentDescription = "iconFilter",
            modifier = Modifier
                .padding(end = 30.dp, top = 10.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(50.dp))
                .clickable { onFilterChange() },
            tint = DarkGreen
        )
    }
}




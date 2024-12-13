package com.app.testetcnicoshopper.View.travelHistory

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.testetcnicoshopper.Model.DTO.TravelListDTO.RidesDTO
import com.app.testetcnicoshopper.View.components.formatDateAndTime

@Composable
fun RidesList(rides: List<RidesDTO>?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        items(rides?.size ?: 0) {
            val ride = rides?.get(it)
            CardDriveComponent(
                name = ride?.driver?.name ?: "",
                value = ride?.value ?: 0.0,
                distance = ride?.distance ?: 0.0,
                duration = ride?.duration ?: "",
                origin = ride?.origin ?: "",
                destination = ride?.destination ?: "",
                time = formatDateAndTime(ride?.date.toString()).second.toString(),
                date = formatDateAndTime(ride?.date.toString()).first.toString()
            )
        }
    }
}

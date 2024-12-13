package com.app.testetcnicoshopper.View.travelHistory.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.testetcnicoshopper.Model.DTO.TravelListDTO.RidesDTO
import com.app.testetcnicoshopper.R
import com.app.testetcnicoshopper.View.components.CustomTextField
import com.app.testetcnicoshopper.ViewModel.TravelViewModel.TravelListViewModel
import com.app.testetcnicoshopper.ui.theme.DarkGreen
import com.app.testetcnicoshopper.ui.theme.LightGreen

@Composable
fun MethodsFilter(
    modifier: Modifier,
    travelListViewModel: TravelListViewModel,
    rides: List<RidesDTO>?,
    onFilterChange: (Boolean) -> Unit
) {
    val filters = listOf(
        stringResource(R.string.Digite_o_nome_do_motorista) to remember { mutableStateOf("") },
        stringResource(R.string.Digite_o_valor_da_viagem) to remember { mutableStateOf("") },
        stringResource(R.string.Digite_a_origem_da_viagem) to remember { mutableStateOf("") },
        stringResource(R.string.Digite_o_destino_da_viagem) to remember { mutableStateOf("") },
    )

    Box(
        modifier = modifier
            .width(340.dp)
            .height(510.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(LightGreen)
    ) {
        Icon(
            imageVector = Icons.Filled.Clear,
            contentDescription = "Clear",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(38.dp)
                .size(36.dp)
                .clickable { onFilterChange(false) },
            tint = Color.White
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
        ) {
            filters.forEach { (label, state) ->
                CustomTextField(
                    value = state.value,
                    onValueChange = { state.value = it },
                    label = label,
                    modifier = Modifier
                        .width(280.dp)
                        .padding(bottom = 15.dp)
                )
            }
            ElevatedButton(
                onClick = {
                    travelListViewModel.applyFilters(
                        rides = rides,
                        driverName = filters[0].second.value,
                        rideValue = filters[1].second.value,
                        origin = filters[2].second.value,
                        destination = filters[3].second.value,
                    )
                    onFilterChange(false)
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .width(280.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = DarkGreen
                )
            ) {
                Text(stringResource(R.string.Adicionar_filtro))
            }
        }
    }
}

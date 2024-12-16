package com.app.testetcnicoshopper.View.travelHistory.filter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.app.testetcnicoshopper.Model.DTO.TravelListDTO.RidesDTO
import com.app.testetcnicoshopper.ViewModel.TravelViewModel.TravelListViewModel

@Composable
fun FilterSection(
    filter: Boolean,
    travelListViewModel: TravelListViewModel,
    onFilterChange: (Boolean) -> Unit,
    rides: List<RidesDTO>?
) {
    if (filter) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            MethodsFilter(
                modifier = Modifier.align(Alignment.TopCenter),
                travelListViewModel = travelListViewModel,
                onFilterChange = onFilterChange,
                rides = rides
            )
        }
    }
}

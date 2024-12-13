package com.app.testetcnicoshopper.View.avaliableDrivers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import coil.compose.rememberAsyncImagePainter
import com.app.testetcnicoshopper.BuildConfig
import com.app.testetcnicoshopper.Model.entities.DriveEntities.RouteResponse
import retrofit2.Response

fun mapsImage(latitudeOrigin: Double, longitudeOrigin: Double, latitudeDestination: Double, longitudeDestination: Double): String {
    val apiKey = BuildConfig.API_KEY
    val imageUrl = "https://maps.googleapis.com/maps/api/staticmap?size=500x500" +
            "&markers=color:red|$latitudeOrigin,$longitudeOrigin" + // Marcador de origem
            "&markers=color:blue|$latitudeDestination,$longitudeDestination" + // Marcador de destino
            "&path=color:0x0000ff|weight:6|$latitudeOrigin,$longitudeOrigin|$latitudeDestination,$longitudeDestination" + // Caminho entre origem e destino
            "&key=${apiKey}"

    return imageUrl
}

@Composable
fun Map(
    response: LiveData<Response<RouteResponse>>,
    modifier: Modifier
){
    val mapImage = response.value?.body()?.let { route ->
        val originMap = route.origin
        val destinationMap = route.destination
        mapsImage(
            latitudeOrigin = originMap.latitude,
            longitudeOrigin = originMap.longitude,
            latitudeDestination = destinationMap.latitude,
            longitudeDestination = destinationMap.longitude
        )
    }
    Image(
        painter = rememberAsyncImagePainter(mapImage),
        contentDescription = "Static Map API",
        modifier = modifier
            .padding(top = 130.dp)
            .width(320.dp)
            .height(320.dp)
            .clip(RoundedCornerShape(57.dp))
    )
}

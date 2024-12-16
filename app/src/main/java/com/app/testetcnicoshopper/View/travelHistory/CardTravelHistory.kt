package com.app.testetcnicoshopper.View.travelHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.testetcnicoshopper.View.avaliableDrivers.NameAndValueDriver
import com.app.testetcnicoshopper.ui.theme.LightGreen

@Composable
fun CardDriveComponent(
    name: String,
    value: Double,
    distance: Double,
    duration: String,
    date: String,
    time: String,
    origin: String,
    destination: String,
) {
    val IsNextCard = remember { mutableStateOf(false) }

    val sizeIconFalse = if (IsNextCard.value) 0.dp else 60.dp
    val sizeIconTrue = if (IsNextCard.value) 60.dp else 0.dp

    Box(
        modifier = Modifier
            .padding(top = 70.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(LightGreen)
            .width(460.dp)
            .height(260.dp)
    ) {
        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "arrowRight",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(50.dp))
                .size(sizeIconFalse)
                .clickable {
                    IsNextCard.value = true
                })
        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "arrowLeft",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clip(RoundedCornerShape(50.dp))
                .size(sizeIconTrue)
                .clickable {
                    IsNextCard.value = false
                })
        when (IsNextCard.value) {
            true -> CardDetails(
                duration = duration,
                distance = distance,
                date = date,
                time = time,
                modifier = Modifier
            )

            false -> MainCard(
                name = name, value = value, origin = origin, destination = destination
            )
        }
    }
}

@Composable
private fun MainCard(
    name: String,
    value: Double,
    origin: String,
    destination: String,
) {

    Column {
        NameAndValueDriver(
            name, value
        )
        Column {
            Text(
                "Origem da viagem: $origin",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 30.dp, bottom = 20.dp)
                    .width(200.dp)
            )
            Text(
                "Destino da viagem: $destination",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 30.dp)
                    .width(200.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }

}

@Composable
fun CardDetails(
    duration: String,
    distance: Double,
    date: String,
    time: String,
    modifier: Modifier,
) {
    val formattedDistance = String.format("%.2f", distance)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        Text(
            date,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Text(
            time,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp),
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Distância percorrida: ${formattedDistance} KM",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp),
            fontSize = 15.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Duração: ${duration}",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp),
            fontSize = 15.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}


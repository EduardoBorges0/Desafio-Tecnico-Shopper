package com.app.testetcnicoshopper.View.avaliableDrivers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.testetcnicoshopper.Model.entities.DriveEntities.RouteResponse
import com.app.testetcnicoshopper.R
import retrofit2.Response
import java.text.NumberFormat
import java.util.Locale

@Composable
fun NameAndValueDriver(name : String, value: Double){
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    Row{
        Text(
            name,
            color = Color.White,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 30.dp, top = 30.dp, bottom = 15.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(format.format(value).toString(),
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(end = 33.dp)
        )
    }
}

@Composable
fun RatingCard(response: Response<RouteResponse>?, index: Int){
    Box (modifier = Modifier.fillMaxSize()){
        val rating = response?.body()!!.options[index].review.rating
        val comment = response?.body()!!.options[index].review.comment
        val ratingImage = when (rating) {
            1 -> R.drawable.onerating
            2 -> R.drawable.tworating
            3 -> R.drawable.threerating
            4 -> R.drawable.fourating
            5 -> R.drawable.fiverating
            else -> "Não avaliado" // Caso não haja avaliação
        }
        AsyncImage(model = ratingImage,
            contentDescription = "$rating star rating",
            modifier = Modifier.size(140.dp).align(Alignment.TopCenter))
        Text(
            comment,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 100.dp)
                .size(200.dp)
        )

    }
}

package com.app.testetcnicoshopper.View.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.app.testetcnicoshopper.R

@Composable
fun Background(){
    AsyncImage(
        model = R.drawable.background,
        contentDescription = "background",
        modifier = Modifier.fillMaxSize()

    )
}

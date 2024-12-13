package com.app.testetcnicoshopper.View.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.app.testetcnicoshopper.ui.theme.LightGreen

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Gray.copy(alpha = 0.3f))){
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center),
            color = LightGreen
        )
    }
}


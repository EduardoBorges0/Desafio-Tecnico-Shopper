package com.app.testetcnicoshopper.View.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.testetcnicoshopper.ui.theme.DarkGreen

@Composable
fun IconPopUp(navHostController: NavHostController){
    Icon(
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
        contentDescription = "arrowLeftpopStack",
        tint = DarkGreen,
        modifier = Modifier
            .padding(36.dp)
            .padding(top = 30.dp)
            .size(30.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navHostController.popBackStack()
            }
    )
}

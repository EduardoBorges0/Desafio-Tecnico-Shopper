package com.app.testetcnicoshopper.View.travelHistory.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.testetcnicoshopper.ui.theme.DarkGreen

@Composable
fun ButtonFilter(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Column(
        modifier = modifier
    ) {
        ElevatedButton(
            enabled = enabled,
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkGreen,
            )
        ) {
            Text(text, color = Color.White)
        }


    }
}

package com.app.testetcnicoshopper.View.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    TextField(
        enabled = enabled,
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.DarkGray,
            unfocusedLabelColor = Color.Gray
        ),
        modifier = modifier.width(350.dp)
    )
}

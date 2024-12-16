package com.app.testetcnicoshopper.View.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AlertDialogError(
    isDialogOpen: Boolean,
    onDismissRequest: () -> Unit,
    mainMessage: String,
    confirmAction: () -> Unit
) {

        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            title = { Text(text = "Erro") },
            text = { Text(mainMessage) },
            confirmButton = {
                TextButton(
                    onClick = {
                        confirmAction()
                    }
                ) {
                    Text(text = "OK", color = Color.White)
                }
            }
        )
}

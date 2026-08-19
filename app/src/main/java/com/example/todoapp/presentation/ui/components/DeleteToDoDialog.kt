package com.example.todoapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todoapp.viewmodel.Todo

@Composable
fun DeleteToDoDialog(
    label: String,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {

    AlertDialog(
        icon = { Icon(imageVector = Icons.Default.Warning, contentDescription = "Warning") },
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("Confirm")
            }
        },
        text = { Text(label) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = { onCancel() }) {
                Text("Cancel")
            }
        }
    )

}
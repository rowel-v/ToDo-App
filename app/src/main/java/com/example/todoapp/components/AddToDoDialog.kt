package com.example.todoapp.components

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun AddToDoDialog(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onClose: () -> Unit
) {

    val textValue = remember { TextFieldState("") }

    AlertDialog(
        modifier = modifier,
        icon = {
            Icon(imageVector = Icons.Default.AddTask, contentDescription = "Add Task")
        },
        text = {
            OutlinedTextField(
                state = textValue,
                placeholder = { Text("Add ToDo") }
            )
        },
        confirmButton = {
            ToDoButton(
                label = "Add",
                onClick = {
                    val newVal = textValue.text.toString()
                    if (!newVal.isEmpty()) {
                        onClick(newVal)
                        onClose()
                    }
                }
            )
        },
        dismissButton = {
            TextButton(
                onClick = { onClose() }
            ) { Text("Cancel") }
        },
        onDismissRequest = { onClose() }
    )

}
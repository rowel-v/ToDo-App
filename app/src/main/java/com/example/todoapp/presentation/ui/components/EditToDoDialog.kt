package com.example.todoapp.presentation.ui.components

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.todoapp.viewmodel.Todo

@Composable
fun EditToDoDialog(
    editToDo: Todo,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onCancel: () -> Unit
) {

    val textValue = remember { TextFieldState(editToDo.name) }

    AlertDialog(
        modifier = modifier,
        icon = {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Task")
        },
        text = {
            OutlinedTextField(
                state = textValue,
                placeholder = { Text("Edit ToDo") }
            )
        },
        confirmButton = {
            ToDoButton(
                label = "Save",
                onClick = {
                    val newVal = textValue.text.toString()
                    if (!newVal.isEmpty()) {
                        onClick(newVal)
                        onCancel()
                    }
                }
            )
        },
        dismissButton = {
            TextButton(
                onClick = { onCancel() }
            ) { Text("Cancel") }
        },
        onDismissRequest = { onCancel() }
    )

}
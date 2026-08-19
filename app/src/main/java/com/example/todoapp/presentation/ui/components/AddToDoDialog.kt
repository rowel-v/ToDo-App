package com.example.todoapp.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.viewmodel.Priority
import com.example.todoapp.viewmodel.Todo

@Composable
fun AddToDoDialog(
    modifier: Modifier = Modifier,
    onClick: (Todo) -> Unit,
    onClose: () -> Unit,
) {

    val toDoName = rememberTextFieldState()
    val toDoDescription = rememberTextFieldState()
    var toDoPriority by rememberSaveable { mutableStateOf(Priority.LOW) }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onClose,
        icon = {
            Icon(imageVector = Icons.Default.AddTask, contentDescription = "Add Task")
        },
        title = {
            Text("Create New Task")
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    state = toDoName,
                    label = { Text("Task Name") },
                    lineLimits = TextFieldLineLimits.SingleLine,
                )
                OutlinedTextField(
                    state = toDoDescription,
                    label = { Text("Description (Optional)") },
                    lineLimits = TextFieldLineLimits.MultiLine(minHeightInLines = 3),
                )

                Column {
                    Text("Priority", style = MaterialTheme.typography.labelMedium)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Priority.entries is the modern, memory-efficient way to loop through Enums
                        Priority.entries.forEach { option ->
                            FilterChip(
                                selected = toDoPriority == option,
                                onClick = { toDoPriority = option }, // Event updates State
                                label = { Text(option.name) }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            ToDoButton(
                label = "Add",
                onClick = {
                    val name = toDoName.text.toString()
                    if (name.isNotBlank()) {
                        onClick(
                            Todo(
                                name = name.trim(),
                                description = toDoDescription.text.toString().trim(),
                                priority = toDoPriority,
                            )
                        )
                        onClose()
                    }
                },
            )
        },
        dismissButton = {
            TextButton(onClick = onClose) {
                Text("Cancel")
            }
        },
    )
}
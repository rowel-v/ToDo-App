package com.example.todoapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.viewmodel.Priority
import com.example.todoapp.viewmodel.Status
import com.example.todoapp.viewmodel.Todo
import com.example.todoapp.viewmodel.toReadableFormat

@Composable
fun TodoInfo(
    todo: Todo,
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onCloseClicked) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                // Top Row: Title & Close Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = todo.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp)
                    )

                    IconButton(
                        onClick = onCloseClicked,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close dialog",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Middle: Date
                Text(
                    text = todo.dateCreated.toReadableFormat(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Bottom Row: Status & Priority Badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Status
                    val isDone = todo.status == Status.DONE
                    Text(
                        text = if (isDone) "Completed" else "Pending",
                        style = MaterialTheme.typography.labelLarge,
                        color = if (isDone) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )

                    // Priority Badge
                    val priorityColor = when (todo.priority) {
                        Priority.HIGH -> MaterialTheme.colorScheme.error
                        Priority.MEDIUM -> MaterialTheme.colorScheme.tertiary
                        Priority.LOW -> MaterialTheme.colorScheme.primary
                    }

                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = priorityColor.copy(alpha = 0.1f), // Soft background
                        contentColor = priorityColor // Bold text
                    ) {
                        Text(
                            text = todo.priority.name,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
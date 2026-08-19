package com.example.todoapp.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    Dialog(
        onDismissRequest = onCloseClicked,
    ) {
        Card(
            modifier = modifier.fillMaxWidth().heightIn(min = 500.dp, max = 700.dp),
            shape = MaterialTheme.shapes.extraLarge, // Standard M3 Dialog Shape
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
            )
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                // 1. Top Row: Title & Close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = todo.name,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f).padding(end = 16.dp)
                    )
                    IconButton(onClick = onCloseClicked, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                // 2. NEW: Scrollable Middle Content
                Column(
                    modifier = Modifier
                        .weight(1f, fill = false) // Takes available space, but doesn't force maximum height
                        .verticalScroll(rememberScrollState()) // Makes this section scrollable!
                ) {
                    if (!todo.description.isNullOrBlank()) {
                        Text(
                            text = todo.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Metadata Dates
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        DateRow(label = "Created:", value = todo.dateCreated.toReadableFormat())
                        todo.dateFinished?.let { finishedAt ->
                            DateRow("Finished:", finishedAt.toReadableFormat(), MaterialTheme.colorScheme.primary)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                // 4. Bottom Row: Chips for Status & Priority
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val isDone = todo.status == Status.DONE
                    InfoChip(
                        text = if (isDone) "Completed" else "Pending",
                        containerColor = if (isDone) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer,
                        contentColor = if (isDone) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onErrorContainer
                    )

                    val priorityColor = when (todo.priority) {
                        Priority.HIGH -> MaterialTheme.colorScheme.error
                        Priority.MEDIUM -> MaterialTheme.colorScheme.tertiary
                        Priority.LOW -> MaterialTheme.colorScheme.primary
                    }
                    InfoChip(
                        text = todo.priority.name,
                        containerColor = priorityColor.copy(alpha = 0.15f),
                        contentColor = priorityColor
                    )
                }
            }
        }
    }
}

// --- Reusable Micro-Components for cleaner code ---

@Composable
private fun DateRow(label: String, value: String, valueColor: Color = MaterialTheme.colorScheme.onSurfaceVariant) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.outline)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, color = valueColor, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun InfoChip(text: String, containerColor: Color, contentColor: Color) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = containerColor,
        contentColor = contentColor
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}
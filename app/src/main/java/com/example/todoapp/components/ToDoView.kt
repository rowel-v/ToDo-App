package com.example.todoapp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.todoapp.viewmodel.Priority
import com.example.todoapp.viewmodel.Status
import com.example.todoapp.viewmodel.ToDoEvent
import com.example.todoapp.viewmodel.Todo

@Composable
fun ToDoView(
    toDo: Todo,
    onClickToDo: (Todo) -> Unit,
    onLongClickToDo: (Todo) -> Unit,
    onClickOptions: (ToDoEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    // Status Badge
    val isDone = toDo.status == Status.DONE

    // 2. Declaratively define the color based on state with a smooth animation
    val cardColor by animateColorAsState(
        targetValue = if (isDone) {
            MaterialTheme.colorScheme.surface // Blends into the background
        } else {
            MaterialTheme.colorScheme.primaryContainer // Highlights active tasks
        },
        label = "Card Color Animation"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .combinedClickable(
                onClick = { onClickToDo(toDo) },
                onLongClick = { onLongClickToDo(toDo) }
            ),
        shape = MaterialTheme.shapes.medium,
        color = cardColor,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // 1. Wrap Title and Priority in a Column
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = toDo.name,
                    style = MaterialTheme.typography.titleMedium,
                )
                // Priority Indicator
                val priorityColor = when (toDo.priority) {
                    Priority.HIGH -> MaterialTheme.colorScheme.error
                    Priority.MEDIUM -> MaterialTheme.colorScheme.tertiary
                    Priority.LOW -> MaterialTheme.colorScheme.primary
                }

                Text(
                    text = toDo.priority.name,
                    style = MaterialTheme.typography.labelSmall,
                    color = priorityColor,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            val doneStatusColor = MaterialTheme.colorScheme.primaryContainer
            val undoneStatusColor = MaterialTheme.colorScheme.errorContainer
            Surface(
                shape = MaterialTheme.shapes.extraSmall,
                color = if (isDone) doneStatusColor else undoneStatusColor,
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Text(
                    text = toDo.status.name,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            var expanded by remember { mutableStateOf(false) }

            // 3. Options Menu
            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Task Options"
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text(if (isDone) "Mark as UnDone" else "Mark as Done") },
                        onClick = {
                            val newStatus = if (isDone) Status.UNDONE else Status.DONE
                            onClickOptions(ToDoEvent.MarkAsDone(toDo.copy(status = newStatus)))
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Edit") },
                        onClick = {
                            onClickOptions(ToDoEvent.Edit(toDo))
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete") },
                        onClick = {
                            onClickOptions(ToDoEvent.DeleteToDo(toDo))
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
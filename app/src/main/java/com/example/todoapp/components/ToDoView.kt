package com.example.todoapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.theme.ToDoColorDone
import com.example.todoapp.ui.theme.ToDoColorUnDone
import com.example.todoapp.viewmodel.Status
import com.example.todoapp.viewmodel.ToDoEvent
import com.example.todoapp.viewmodel.Todo

@Composable
fun ToDoView(
    toDo: Todo,
    onClick: (Todo) -> Unit,
    onClickOptions: (ToDoEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        onClick = { onClick(toDo) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = toDo.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )

            if (toDo.status == Status.DONE) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = ToDoColorDone
                ) {
                    Text(text = "DONE", Modifier.padding(4.dp))
                }
            } else {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = ToDoColorUnDone
                ) {
                    Text(text = "UNDONE", Modifier.padding(4.dp))
                }
            }

            Box {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    modifier = Modifier
                        .clickable { expanded = true }
                        .padding(8.dp)
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {

                    if (toDo.status == Status.UNDONE) {
                        DropdownMenuItem(
                            text = { Text("Mark as Done") },
                            onClick = {
                                onClickOptions(ToDoEvent.MarkAsDone(toDo.copy(status = Status.DONE)))
                                expanded = false
                            }
                        )
                    } else {
                        DropdownMenuItem(
                            text = { Text("Mark as UnDone") },
                            onClick = {
                                onClickOptions(ToDoEvent.MarkAsDone(toDo.copy(status = Status.UNDONE)))
                                expanded = false
                            }
                        )
                    }

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
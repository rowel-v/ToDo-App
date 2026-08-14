package com.example.todoapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.components.AddToDoDialog
import com.example.todoapp.components.DeleteToDoDialog
import com.example.todoapp.components.EditToDoDialog
import com.example.todoapp.components.ToDoListView
import com.example.todoapp.components.TodoInfo
import com.example.todoapp.viewmodel.ToDoEvent
import com.example.todoapp.viewmodel.ToDoUiState
import com.example.todoapp.viewmodel.Todo

@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    toDoUiState: ToDoUiState,
    onEvent: (ToDoEvent) -> Unit,
    addToDoButtonClicked: Boolean = false,
    onCloseAddToDoButton: () -> Unit,
) {

    var showToDoInfo by remember { mutableStateOf<Todo?>(null) }
    var toDoToDelete by remember { mutableStateOf<Todo?>(null) }
    var toDoToUpdate by remember { mutableStateOf<Todo?>(null) }

    Box(
        modifier = modifier
    ) {
        ToDoListView(
            toDos = toDoUiState.toDo,
            onClickToDo = { showToDoInfo = it },
            onLongClickToDo = { showToDoInfo = it },
            onClickToDoOptions = { event ->
                when (event) {
                    is ToDoEvent.MarkAsDone -> onEvent(event)
                    is ToDoEvent.Edit -> toDoToUpdate = event.todo
                    is ToDoEvent.DeleteToDo -> toDoToDelete = event.toDo
                    else -> {}
                }
            },
            modifier = modifier
        )
        if (addToDoButtonClicked) {
            AddToDoDialog(
                onClick = { newToDo ->
                    onEvent(ToDoEvent.AddToDo(newToDo))
                },
                onClose = { onCloseAddToDoButton() },
                modifier = Modifier
            )
        }

        showToDoInfo?.let {
            TodoInfo(
                todo = it,
                onCloseClicked = { showToDoInfo = null },
                modifier = Modifier.size(width = 320.dp, height = 200.dp)
            )
        }
        toDoToDelete?.let { toDo ->
            DeleteToDoDialog(
                label = "Confirm ${toDo.name} to Delete? ",
                onConfirm = {
                    onEvent(ToDoEvent.DeleteToDo(toDo))
                    toDoToDelete = null
                },
                onCancel = { toDoToDelete = null },
            )
        }
        toDoToUpdate?.let { toDo ->
            EditToDoDialog(
                editToDo = toDo,
                onClick = { value ->
                    val editedToDo = toDo.copy(name = value)
                    onEvent(ToDoEvent.UpdateToDo(editedToDo))
                },
                onCancel = { toDoToUpdate = null },
                modifier = Modifier
            )
        }
    }
}
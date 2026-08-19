package com.example.todoapp.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.presentation.ui.components.AddToDoDialog
import com.example.todoapp.presentation.ui.components.DeleteToDoDialog
import com.example.todoapp.presentation.ui.components.EditToDoDialog
import com.example.todoapp.presentation.ui.components.ToDoListView
import com.example.todoapp.presentation.ui.components.TodoInfo
import com.example.todoapp.viewmodel.ToDoEvent
import com.example.todoapp.viewmodel.ToDoUiState
import com.example.todoapp.viewmodel.Todo

sealed interface DialogState {
    data object None : DialogState
    data object Add : DialogState
    data class Info(val todo: Todo) : DialogState
    data class Delete(val todo: Todo) : DialogState
    data class Edit(val todo: Todo) : DialogState
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    toDoUiState: ToDoUiState,
    onEvent: (ToDoEvent) -> Unit,
) {

    var activeDialog by remember { mutableStateOf<DialogState>(DialogState.None) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("To Do App") }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { activeDialog = DialogState.Add },
                icon = { Icon(Icons.Filled.Add, contentDescription = "Add Icon") },
                text = { Text("Add To Do") }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ToDoListView(
                toDos = toDoUiState.toDo,
                onClickToDo = { activeDialog = DialogState.Info(it) },
                onClickToDoOptions = { event ->
                    when (event) {
                        is ToDoEvent.MarkAsDone -> onEvent(event)
                        is ToDoEvent.Edit -> activeDialog = DialogState.Edit(event.todo)
                        is ToDoEvent.DeleteToDo -> activeDialog = DialogState.Delete(event.toDo)
                        else -> {}
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            when (val state = activeDialog) {
                is DialogState.None -> Unit
                is DialogState.Add ->
                    AddToDoDialog(
                        onClick = { newToDo ->
                            onEvent(ToDoEvent.AddToDo(newToDo))
                            activeDialog = DialogState.None
                        },
                        onClose = { activeDialog = DialogState.None },
                        modifier = Modifier
                    )

                is DialogState.Info ->
                    TodoInfo(
                        todo = state.todo,
                        onCloseClicked = { activeDialog = DialogState.None },
                        modifier = Modifier.size(width = 320.dp, height = 200.dp)
                    )

                is DialogState.Delete ->
                    DeleteToDoDialog(
                        label = "Confirm ${state.todo.name} to Delete? ",
                        onConfirm = {
                            onEvent(ToDoEvent.DeleteToDo(state.todo))
                            activeDialog = DialogState.None
                        },
                        onCancel = { activeDialog = DialogState.None },
                    )

                is DialogState.Edit ->
                    EditToDoDialog(
                        editToDo = state.todo,
                        onClick = { value ->
                            onEvent(ToDoEvent.UpdateToDo(state.todo.copy(name = value)))
                            activeDialog = DialogState.None
                        },
                        onCancel = { activeDialog = DialogState.None },
                        modifier = Modifier
                    )
            }
        }
    }
}
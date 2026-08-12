package com.example.todoapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todoapp.viewmodel.ToDoEvent
import com.example.todoapp.viewmodel.Todo

@Composable
fun ToDoListView(
    toDos: List<Todo>,
    onClickToDo: (Todo) -> Unit,
    onClickToDoOptions: (ToDoEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = toDos,
            key = { toDo -> toDo.id }
        ) { currentToDo ->
            ToDoView(
                toDo = currentToDo,
                onClick = { onClickToDo(it) },
                onClickOptions = { onClickToDoOptions(it) },
                modifier = Modifier
                    .fillMaxWidth()
            )
            HorizontalDivider()
        }

    }

}
package com.example.todoapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.viewmodel.ToDoEvent
import com.example.todoapp.viewmodel.Todo

@Composable
fun ToDoListView(
    toDos: List<Todo>,
    onClickToDo: (Todo) -> Unit,
    onLongClickToDo: (Todo) -> Unit,
    onClickToDoOptions: (ToDoEvent) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp)
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding, // 1. Prevents clipping at the top/bottom of screen
        verticalArrangement = Arrangement.spacedBy(12.dp) // 2. Clean, consistent spacing between items
    ) {
        items(
            items = toDos,
            key = { it.id } // Excellent work including this!
        ) { currentToDo ->
            ToDoView(
                toDo = currentToDo,
                onClickToDo = onClickToDo, // 3. Idiomatic lambda passing
                onLongClickToDo = onLongClickToDo,
                onClickOptions = onClickToDoOptions,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
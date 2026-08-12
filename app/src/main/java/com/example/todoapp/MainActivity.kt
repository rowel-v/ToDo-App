package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todoapp.components.ToDoButton
import com.example.todoapp.screen.ToDoScreen
import com.example.todoapp.viewmodel.ToDoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // ToDoAppTheme {

            val toDoViewModel = ToDoViewModel()
            val toDoUiState = toDoViewModel.toDoUiState.collectAsStateWithLifecycle()

            var showAddToDoButton by remember { mutableStateOf(false) }

            Scaffold(
                modifier = Modifier,
                topBar = {
                    Text(
                        text = "To Do App",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = (24*2).dp, start = 24.dp)
                    )
                },
                floatingActionButton = {
                    ToDoButton(
                        label = "Add To Do",
                        onClick = { showAddToDoButton = true },
                    )
                }
            ) { innerPadding ->
                ToDoScreen(
                    toDoUiState = toDoUiState.value,
                    onEvent = toDoViewModel::onEvent,
                    addToDoButtonClicked = showAddToDoButton,
                    onCloseAddToDoButton = { showAddToDoButton = false },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
            // }
        }
    }
}
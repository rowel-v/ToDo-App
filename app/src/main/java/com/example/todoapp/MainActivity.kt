package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.screen.ToDoScreen
import com.example.todoapp.viewmodel.ToDoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // ToDoAppTheme {

            val toDoViewModel: ToDoViewModel = viewModel()
            val toDoUiState by toDoViewModel.toDoUiState.collectAsStateWithLifecycle()

            ToDoScreen(
                toDoUiState = toDoUiState,
                onEvent = toDoViewModel::onEvent,
                modifier = Modifier,
            )
            // }
        }
    }
}
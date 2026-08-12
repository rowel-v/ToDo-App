package com.example.todoapp.viewmodel

data class ToDoUiState(
    val toDo: List<Todo> = emptyList(),
    val isLoading: Boolean = false
)
package com.example.todoapp.viewmodel

sealed interface ToDoEvent {
    data class AddToDo(val toDo: Todo) : ToDoEvent
    data class DeleteToDo(val toDo: Todo) : ToDoEvent
    data class UpdateToDo(val toDo: Todo) : ToDoEvent

    data class MarkAsDone(val toDo: Todo) : ToDoEvent
    data class Edit(val todo: Todo) : ToDoEvent
}
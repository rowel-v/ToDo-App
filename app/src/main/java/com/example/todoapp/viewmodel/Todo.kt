package com.example.todoapp.viewmodel

data class Todo(
    val id: Int = 0,
    val name: String,
    val status: Status = Status.UNDONE
)

enum class Status {
    DONE, UNDONE
}
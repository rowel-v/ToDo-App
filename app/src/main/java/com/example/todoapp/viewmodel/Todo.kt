package com.example.todoapp.viewmodel

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Todo(
    val id: Int = 0,
    val name: String,
    val description: String? = null,
    val priority: Priority,
    val status: Status = Status.UNDONE,
    val dateCreated: LocalDateTime = LocalDateTime.now(),
)

enum class Status { DONE, UNDONE }
enum class Priority { LOW, MEDIUM, HIGH }

fun LocalDateTime.toReadableFormat(): String =
    format(DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a"))
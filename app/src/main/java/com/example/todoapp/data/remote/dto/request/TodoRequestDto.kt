package com.example.todoapp.data.remote.dto.request

import com.example.todoapp.data.remote.dto.common.TodoPriority
import com.example.todoapp.data.remote.dto.common.TodoStatus
import java.time.LocalDateTime

data class TodoRequestDto(
    val name: String? = null,
    val description: String? = null,
    val status: TodoStatus? = null,
    val priority: TodoPriority? = null,
    val dueDate: LocalDateTime? = null,
)

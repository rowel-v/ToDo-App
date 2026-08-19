package com.example.todoapp.data.remote.dto.response

import com.example.todoapp.data.remote.dto.common.TodoPriority
import com.example.todoapp.data.remote.dto.common.TodoStatus
import com.example.todoapp.domain.model.Todo
import java.time.LocalDateTime
import kotlin.Long

data class TodoResponseDto(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val status: TodoStatus? = null,
    val priority: TodoPriority? = null,
    val dueDate: LocalDateTime? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {
    fun toDomain(): Todo = Todo(
        id = id,
        name = name,
        description = description,
        status = status,
        priority = priority,
        dueDate = dueDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}


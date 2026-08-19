package com.example.todoapp.domain.model

import com.example.todoapp.data.remote.dto.common.TodoPriority
import com.example.todoapp.data.remote.dto.common.TodoStatus
import com.example.todoapp.data.remote.dto.request.TodoRequestDto
import java.time.LocalDateTime

data class Todo(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val status: TodoStatus? = null,
    val priority: TodoPriority? = null,
    val dueDate: LocalDateTime? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {

    fun toRequestDto(): TodoRequestDto = TodoRequestDto(
        name = name,
        description = description,
        status = status,
        priority = priority,
        dueDate = dueDate
    )
}

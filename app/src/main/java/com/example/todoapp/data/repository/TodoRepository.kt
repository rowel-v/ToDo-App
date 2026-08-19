package com.example.todoapp.data.repository

import com.example.todoapp.data.remote.TodoApiService
import com.example.todoapp.domain.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(val todoApiService: TodoApiService) {

    suspend fun getAllTodo(): Result<List<Todo>> = runCatching {
        val response = todoApiService.getAllTodo()
        if (response.isSuccessful) {
            response.body()?.data?.map { it.toDomain() } ?: emptyList()
        } else {
            error("API Error: ${response.code()}")
        }
    }

    suspend fun createTodo(todo: Todo): Result<Todo> = runCatching {
        val response = todoApiService.createTodo(todo.toRequestDto())
        if (response.isSuccessful) {
            response.body()?.data?.toDomain()
                ?: throw Exception("Creation succeeded, but backend returned null data")
        } else {
            return Result.failure(Exception("API Error: ${response.code()}"))
        }
    }

    suspend fun deleteTodo(todoId: Long): Result<Unit> = runCatching {
        val response = todoApiService.deleteTodo(todoId)
        if (!response.isSuccessful) {
            throw Exception("Deletion failed with status: ${response.code()}")
        }
    }
}
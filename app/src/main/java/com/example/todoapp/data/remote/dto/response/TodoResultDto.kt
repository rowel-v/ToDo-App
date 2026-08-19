package com.example.todoapp.data.remote.dto.response

data class TodoResultDto<T>(
    val data: T,
    val message: String,
)

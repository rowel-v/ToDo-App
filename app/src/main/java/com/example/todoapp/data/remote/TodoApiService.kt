package com.example.todoapp.data.remote

import com.example.todoapp.data.remote.dto.request.TodoRequestDto
import com.example.todoapp.data.remote.dto.response.TodoResultDto
import com.example.todoapp.data.remote.dto.response.TodoResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TodoApiService {

    @GET("todos")
    suspend fun getAllTodo(): Response<TodoResultDto<List<TodoResponseDto>>>

    @POST("todos")
    suspend fun createTodo(@Body req: TodoRequestDto): Response<TodoResultDto<TodoResponseDto>>

    @DELETE("todos/{todoId}")
    suspend fun deleteTodo(@Path("todoId") todoId: Long): Response<Unit>

}
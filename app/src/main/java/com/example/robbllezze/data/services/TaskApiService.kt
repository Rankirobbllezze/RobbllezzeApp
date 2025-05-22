package com.example.robbllezze.data.services

import com.example.robbllezze.data.model.CreateTaskRequest
import com.example.robbllezze.data.model.Task
import org.checkerframework.common.reflection.qual.GetClass

interface TaskApiService {
//    add a notation to represent the http method
    @GET("/api/tasks")
    suspend fun getTasks(): List<Task>
    @POST("api/tasks")
    suspend fun createTask(@Body task: CreateTaskRequest) : Task
}
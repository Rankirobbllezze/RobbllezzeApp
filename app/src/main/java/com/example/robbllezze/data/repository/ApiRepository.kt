package com.example.robbllezze.data.repository

import com.example.robbllezze.data.model.Task
import com.example.robbllezze.data.services.TaskApiService

class ApiRepository(private val apiService: TaskApiService) {
    suspend fun getTasks(): List<Task>{
        return apiService.getTasks()
    }
}
package com.example.robbllezze.data.services

import com.example.robbllezze.data.model.CreateTaskRequest
import com.example.robbllezze.data.model.Task
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskApiService {
//    add a notation to represent the http method
    @GET("/api/tasks")
    suspend fun getTasks(): List<Task>
    @POST("api/tasks")
    suspend fun createTask(@Body task: CreateTaskRequest) : Task

    //companion object : this object is accessible in any
    //implementing the interface
    companion object{
        private val base_url = "" // server link ' ngrok link function to create connection to endpoint
        fun create(): TaskApiService {
            // return retrofit which gets up our network communication
            val retrofit = Retrofit.Builder().baseUrl(base_url).addConverterFactory(
                GsonConverterFactory.create()).build()
            return retrofit.create(TaskApiService::class.java)
        }
    }
}
package com.example.robbllezze.data.services

import com.example.robbllezze.data.model.CreateTaskRequest
import com.example.robbllezze.data.model.Task
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskApiService {
    // add annotations to represent the HTTP method
    @GET("api/tasks")
    suspend fun getTasks(): List<Task>
    @POST("api/tasks")
    suspend fun createTask(@Body task: CreateTaskRequest) : Task

    // companion object : this object is accessible in any class
    // implementing the interface
    companion object{
        private val base_url = "https://5235-197-237-118-180.ngrok-free.app/" // server link , ngrok link
        // function to create connection to endpoint
        fun create(): TaskApiService {
            // return retrofit which set's up our network communication
            val retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(TaskApiService::class.java)
        }
    }
}









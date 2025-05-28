package com.example.robbllezze.data.services

import com.example.robbllezze.data.model.CreateTaskRequest
import com.example.robbllezze.data.model.Task
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskApiService {
    // add annotations to represent the HTTP method
    @GET("api/tasks")
    suspend fun getTasks(): List<Task>
    @POST("api/tasks/")
    suspend fun createTask(@Body task: CreateTaskRequest) : Task
    @PUT("api/tasks/{id}/")
    suspend fun updateTask(@Path("id") id: Int, @Body TASK: CreateTaskRequest) : Task
    @DELETE("api/tasks/{id}/")
    suspend fun deleteTask(@Path("id") id: Int,)


    // companion object : this object is accessible in any class
    // implementing the interface
    companion object{
        private val base_url = "https://2009-197-237-118-180.ngrok-free.app" // server link , ngrok link
        // function to create connection to endpoint
        fun create(): TaskApiService {
            // return retrofit which set's up our network communication
            //gson will reference logs for the networking process
            //i.e the http status code: 200 - ok 404 - page note found
            //500 - internal server error 400 - bad request error
            val gson = GsonBuilder().serializeNulls().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(TaskApiService::class.java)
        }
    }
}









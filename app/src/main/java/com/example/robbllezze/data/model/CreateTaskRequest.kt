package com.example.robbllezze.data.model
//model will define the body of our post request i.e. posting our task via our api
data class CreateTaskRequest(
    val title: String,
    val completed: Boolean = false,
    val tasker: Int,
    val user: Int? = null
)

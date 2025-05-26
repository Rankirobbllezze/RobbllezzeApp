package com.example.robbllezze.data.model
//model will define the body of our post request i.e. posting our task via our api
data class CreateTaskRequest(
    val tasker_detail: Int,
    val title: String,
    val completed: Boolean = false,
    val user: Int? = null
)

package com.example.robbllezze.data.model

import com.google.gson.annotations.SerializedName // to ensure our fields are received on post

//model will define the body of our post request i.e. posting our task via our api
data class CreateTaskRequest(
    @SerializedName("tasker")
    val tasker: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("completed")
    val completed: Boolean = false,
    @SerializedName("user")
    val user: Int? = null
)

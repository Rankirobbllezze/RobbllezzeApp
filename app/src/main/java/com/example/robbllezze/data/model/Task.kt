package com.example.robbllezze.data.model
//null fields represent the sections
//in json i.e. the backed that can be null
data class Task(
    val id: Int? = null,
    val tasker: Tasker,
    val title: String,
    val completed: Boolean,
    val created_at: String? = null,
    val user: Int? = null,
    val tasker_detail: Int
)

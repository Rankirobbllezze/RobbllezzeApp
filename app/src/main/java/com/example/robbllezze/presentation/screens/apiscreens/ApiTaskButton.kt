package com.example.robbllezze.presentation.screens.apiscreens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.robbllezze.data.repository.ApiRepository
import com.example.robbllezze.data.services.TaskApiService

// here implement the form to capture the values more dynamically
@Composable
fun ApiTaskButton(
    navController: NavController,
    viewModel: ApiViewModel = viewModel(
        factory = ApiViewModelFactory(ApiRepository(
            TaskApiService.create()
        ))
    )
){
    Button(onClick = {
        viewModel.postTasks(
            taskerId = 1,
            title = "New Task from Android",
            completed = false,
            userId = null
        )
    }) {
        Text("Post to Api")
    }
    //show feedback
    val result = viewModel.taskCreationState.value
    when {
        result?.isSuccess == true -> {
            val task = result.getOrNull()
            Text("Task created successfully:, ${task?.title}")
        }
        result?.isFailure == true -> {
            val error = result.exceptionOrNull()
            Text("Error:, ${error?.message}")
        }
    }
}
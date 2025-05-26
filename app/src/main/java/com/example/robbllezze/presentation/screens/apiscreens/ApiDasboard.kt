package com.example.robbllezze.presentation.screens.apiscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.robbllezze.presentation.components.TodoItemCard


@Composable
fun ApiDashboard(
    navController: NavController,
    viewModel: ApiViewModel = hiltViewModel()
){
    //reference variable for items
    val tasks by viewModel.tasks
    val isLoading by viewModel.isLoading
    val error by viewModel.error

    //calling the action to fetch tasks from viewModel on screen load
    LaunchedEffect(Unit) {
        viewModel.fetchTasks()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if(isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        //if not
        //is it an error
        error?.let {
            Text(text = it,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
                color = MaterialTheme.colorScheme.error)
        }
        //list of tasks from api
        LazyColumn {
            items(tasks) { task ->
                TodoItemCard(
                todo = task,
                onCompleteChange = {},
                onEditClick = {},
                onDeleteClick = {}

            )
            }
        }
    }


}
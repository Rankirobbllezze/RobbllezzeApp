package com.example.robbllezze.presentation.screens.dashboard

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.robbllezze.presentation.components.TodoItemCard

// THIS FILE WILL CONTAIN THE COMPOSABLE ELEMENTS TO DISPLAY MY LIST OF TODos
@Composable
fun DashboardScreen(viewModel: DashboardViewModel = viewModel()){
    // fetch our todos from the viewmodel
    val todos by viewModel.todos.collectAsState()
    // to create a list of composables {listview}
    LazyColumn {

        items(todos){ todo -> TodoItemCard(
            // passing info to the composable
            todo = todo,
            onCompleteChange= {viewModel.toogleTodoCompletion(todo.id)}
        )

        }

    }
}



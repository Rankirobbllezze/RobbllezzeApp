package com.example.robbllezze.presentation.screens.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.robbllezze.presentation.components.TodoItemCard
import com.example.robbllezze.presentation.screens.addtodo.AddToDoForm

// THIS FILE WILL CONTAIN THE COMPOSABLE ELEMENTS TO DISPLAY MY LIST OF TODos
@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()){
    // fetch our todos from the viewmodel
    val todos by viewModel.todos.collectAsState()
    // to create a list of composables {listview}
    // add a dialog
    val showAddDialog = remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {showAddDialog.value = true}
            ) {
                Icon(Icons.Default.Add,
                    contentDescription = "Add Todo")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {

            items(todos){ todo -> TodoItemCard(
                // passing info to the composable
                todo = todo,
                onCompleteChange= {viewModel.toogleTodoCompletion(todo.id)}
            )

            }

        }

        // SHOW POP UP IF THE ALERT DIALOG IS TRUE
        if(showAddDialog.value){
            // show pop up
            // AlertDialog is used to show pop ups
            AlertDialog(
                onDismissRequest = { showAddDialog.value = false},
                title = { Text("Add Todo") },
                text = {
                    AddToDoForm(
                        viewModel = viewModel,
                        onDismiss = {showAddDialog.value = false}
                    )
                },
                confirmButton = {},
                dismissButton = {}
            )
        }

    }
}



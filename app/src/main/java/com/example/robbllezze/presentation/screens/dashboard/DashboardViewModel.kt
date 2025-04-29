package com.example.robbllezze.presentation.screens.dashboard

import androidx.lifecycle.ViewModel
import com.example.robbllezze.data.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : ViewModel() {
    // mock data // hard coded  [ {} , {}      ]
    private val mockRobbllezze = listOf(
        TodoItem(
            id = 1,
            title = "Buy Groceries",
            description = "Milk, Eggs , and Bread",
            imageUri = null,
            tasker = "Joseph",
            isCompleted = false
        ) ,
        TodoItem(
            id = 2,
            title = "Finish Android Phase",
            description = "Complete JETPACK UI END OF THIS WEEK",
            imageUri = null,
            tasker = "Team",
            isCompleted = false
        ) ,
        TodoItem(
            id = 3,
            title = "Travel",
            description = "Travel back home @ 3PM Saturday",
            imageUri = null,
            tasker = "Me",
            isCompleted = true
        )
    )
    // Create a stateflow : this is simply making the above data publicly visible to all
    // composables
    //1. Keep the mutable state flow private
    private val _todos = MutableStateFlow(mockRobbllezze)
    // 2. Expose as a public state to composables
    val todos = _todos.asStateFlow()
    // [ {} , {} , { }  ]
    // functions working on the data being observed
    // 1 ....... 10000000000000000000000000000000000000000000000000000
    fun toogleTodoCompletion(todoId: Long){
        // 1. Making a reference to all todos
        // 2. Reassign the todos value according to the new state for
        // isCompleted
        // 3. this step will allow new renders on a todoitem change
        // 4. itortodo' references a single todoitem
        _todos.value = _todos.value.map{ todo ->
            if(todo.id == todoId) todo.copy(isCompleted = !todo.isCompleted)
            else todo

        }
    }

}








package com.example.robbllezze.presentation.screens.dashboard

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.robbllezze.data.model.TodoItem
import com.example.robbllezze.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    // mock data // hard coded  [ {} , {}      ]
//    private val mockToDO = listOf(
//         TodoItem(
//             id = 1,
//             title = "Buy Groceries",
//             description = "Milk, Eggs , and Bread",
//             imageUri = null,
//             tasker = "Joseph",
//             isCompleted = false
//         ) ,
//        TodoItem(
//            id = 2,
//            title = "Finish Android Phase",
//            description = "Complete JETPACK UI END OF THIS WEEK",
//            imageUri = null,
//            tasker = "Team",
//            isCompleted = false
//        ) ,
//        TodoItem(
//            id = 3,
//            title = "Travel",
//            description = "Travel back home @ 3PM Saturday",
//            imageUri = null,
//            tasker = "Me",
//            isCompleted = true
//        )
//    )
    // Create a stateflow : this is simply making the above data publicly visible to all
    // composables
    //1. Keep the mutable state flow private
//    private val _todos = MutableStateFlow(mockToDO)
//    // 2. Expose as a public state to composables
//    val todos = _todos.asStateFlow()
    // GETTING THE DATA FROM THE REPOSITORY LAYER
    val todos: StateFlow<List<TodoItem>> = repository.getAllTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    // GETTING OUR TODOS FROM FIREBASE
    val firebaseTodos: StateFlow<List<TodoItem>>
            = repository.fetchtodosFromFirebase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    // expose the update and delete
    fun deleteTodoFromFirebase(todo: TodoItem){
        viewModelScope.launch {
            repository.deleteTodoFirebase(todo)
        }
    }
    fun updateTodoFromFirebase(todo: TodoItem){
        viewModelScope.launch {
            repository.updateTodoFirebase(todo)
        }
    }
    // [ {} , {} , { }  ]
    // functions working on the data being observed
    // 1 ....... 10000000000000000000000000000000000000000000000000000
    fun toogleTodoCompletion(todoId: Int){
        // 1. Making a reference to all todos
        // 2. Reassign the todos value according to the new state for
        // isCompleted
        // 3. this step will allow new renders on a todoitem change
        // 4. itortodo' references a single todoitem
//        _todos.value = _todos.value.map{ todo ->
//            if(todo.id == todoId) todo.copy(isCompleted = !todo.isCompleted)
//            else todo
//
//        }
        // observing changes in the repository to update the viewmodel
        viewModelScope.launch {
            val todo = repository.getTodoById(todoId) ?: return@launch
            val updateTodo = todo.copy(isCompleted = !todo.isCompleted)
            repository.updateTodo(updateTodo)
        }
    }

    // function to add data
    fun addToDO (title: String, description: String,
                 tasker:String, imageUri: Uri?){
        viewModelScope.launch {
            var imageUrl: String? = null
            if(imageUri != null){
                //  imageUrl = repository.uploadImagetoFirebase(imageUri)
            }
            // we create the new Item
            val newTodo = TodoItem(
                id = 0, title = title,
                description = description, imageUri = imageUrl,
                tasker = tasker, isCompleted = false
            )
            // room db insert
            repository.insertTodo(newTodo)
            //firebase insert
            repository.uploadToFirebase(newTodo)
        }
    }

}










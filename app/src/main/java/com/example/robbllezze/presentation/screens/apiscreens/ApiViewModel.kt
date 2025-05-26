package com.example.robbllezze.presentation.screens.apiscreens

import android.util.Log
import android.view.PixelCopy.request
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.robbllezze.data.model.CreateTaskRequest
import com.example.robbllezze.data.model.Task
import com.example.robbllezze.data.repository.ApiRepository
import kotlinx.coroutines.launch

class ApiViewModel(private val
                   repository: ApiRepository)
    : ViewModel(){
    // reference variables
    // this will capture tasks from our returned api data
    private val _tasks = mutableStateOf<List<Task>>(emptyList())
    // the supply of above tasks to our composable screens
    val tasks: State<List<Task>> = _tasks
    // reference to the data loading state i.e is it loaded or not
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    // reference to any occurance of an error
    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error
    // reference to the post process i.e. whether its successful or not
    private val _taskCreationState = mutableStateOf<Result<Task>?>(null)
    val taskCreationState: State<Result<Task>?> = _taskCreationState

    // function fetch our tasks
    fun fetchTasks(){
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try  {
                _tasks.value = repository.getTasks()
            } catch (e: Exception){
                _error.value = e.message ?: "Unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
    // post tasks
    fun postTasks(taskerId: Int, title: String, completed: Boolean, userId: Int? = null){
        viewModelScope.launch {
            try {
                // define our new task
                val request = CreateTaskRequest(tasker_detail = taskerId, title = title, completed = completed,
                    user =  userId)
                //send using retrofit and capture the response from retrofit
                val task = repository.createTask(request)
                _taskCreationState.value = Result.success(task)
            } catch (e: Exception){
                _taskCreationState.value = Result.failure(e)
                Log.e("POST", e.message.toString())
            }
        }

    }


}

// VIEWMODEL FACTORY
class ApiViewModelFactory(private val repository: ApiRepository) :
    ViewModelProvider.Factory {
    override fun<T : ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(ApiViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ApiViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}









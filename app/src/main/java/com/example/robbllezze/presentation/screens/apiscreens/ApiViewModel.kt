package com.example.robbllezze.presentation.screens.apiscreens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.robbllezze.data.model.Task
import com.example.robbllezze.data.repository.ApiRepository
import kotlinx.coroutines.launch

class ApiViewModel(private val repository: ApiRepository) : ViewModel() {
    //reference variables
    //this will capture tasks from our returned api data
    private val _tasks = mutableStateOf<List<Task>>(emptyList())
    // the supply of above tasks to our composable screens
    val tasks: State<List<Task>> = _tasks
    //reference to the data loading state i.e. is it loaded or not
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    //reference to any occurance of an error
    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

//    function fetch tasks
  fun fetchTasks(){
     viewModelScope.launch {
        _isLoading.value = true
        _error.value = null
        try {
            _tasks.value = repository.getTasks()
        } catch (e: Exception){
            _error.value = e.message ?: "Unknown error occurred"
        } finally {
            _isLoading.value = false
        }
     }
  }
}
//VIEWMODEL FACTORY
class ApiViewModelFactory(private val repository: ApiRepository) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApiViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.robbllezze.data.repository

import android.net.Uri
import com.example.robbllezze.data.model.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockToDoRepository : TodoRepository {
    private val todos  = mutableListOf(
        TodoItem(1,"1","Buy Groceries","Milk",
            null,"Joseph", 2025,
            false),
        TodoItem(2,"2","Finish Room DB","Todo",
            null,"Joseph", 2025,
            false),
        TodoItem(3,"3","Carry out demos","Android demos",
            null,"Class", 2025,
            false)
    )
    private var nextId = 4
    override fun getAllTodos(): Flow<List<TodoItem>> {
        return flowOf(todos.toList())
    }

    override fun fetchtodosFromFirebase(): Flow<List<TodoItem>> {
        return flowOf(todos.toList())
    }

    override suspend fun getTodoById(id: Int): TodoItem? {
        return todos.find {it.id  == id}
    }
    override suspend fun insertTodo(todo: TodoItem) {
        todos.add(todo.copy(id = nextId++ ))
    }
    override suspend fun deleteTodo(todo: TodoItem) {
        todos.removeIf{it.id == todo.id}
    }
    override suspend fun updateTodo(todo: TodoItem) {
        val index = todos.indexOfFirst {it.id == todo.id}
        if (index != -1) {
            todos[index] = todo
        }
    }

    override suspend fun uploadToFirebase(todo: TodoItem) {
        // here simulation of the upload process
    }

    override suspend fun uploadImagetoFirebase(imageUri: Uri?): String {
        // here simulation for storage process
        return "mock.url"
    }

    override suspend fun updateTodoFirebase(todo: TodoItem) {
        //
    }

    override suspend fun deleteTodoFirebase(todo: TodoItem) {
        //
    }

}
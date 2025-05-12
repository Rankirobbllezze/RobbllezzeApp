package com.example.robbllezze.data.repository

import com.example.robbllezze.data.model.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockToDoRepository : TodoRepository {
    private val todos = mutableListOf(
        TodoItem(1, "Buy Electronics", "Computer", null, "Robbllezze", 2025, false),
        TodoItem(2, "Finish my coding", "Todo", null, "Robena", 2025, false),

    )
    private var nextId = 3
    override fun getAllTodos(): Flow<List<TodoItem>> {
      return flowOf(todos.toList())
    }

    override suspend fun getTodoById(id: Int): TodoItem? {
        return todos.find {it.id == id}
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

}
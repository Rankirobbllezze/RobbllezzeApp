package com.example.robbllezze.data.repository

import com.example.robbllezze.data.dao.TodoDAO
import com.example.robbllezze.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoItem>>
    suspend fun getTodoById(id: Int): TodoItem?
    suspend fun insertTodo(todo: TodoItem)
    suspend fun deleteTodo(todo: TodoItem)
    suspend fun updateTodo(todo: TodoItem)

}
class TodoRepositoryImpl(private val todoDao: TodoDAO) :
        TodoRepository{
    override fun getAllTodos(): Flow<List<TodoItem>> {
        return todoDao.getAllTodos()
    }

    override suspend fun getTodoById(id: Int): TodoItem? {
       return todoDao.getTodoById(id)
    }

    override suspend fun insertTodo(todo: TodoItem) {
        return todoDao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: TodoItem) {
        return todoDao.deleteTodo(todo)
    }

    override suspend fun updateTodo(todo: TodoItem) {
        return todoDao.updateTodo(todo)
    }

}
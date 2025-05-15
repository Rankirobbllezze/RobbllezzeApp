package com.example.robbllezze.data.repository

import android.net.Uri
import com.example.robbllezze.data.dao.TodoDAO
import com.example.robbllezze.data.model.TodoItem
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import java.util.UUID

interface TodoRepository{
    fun getAllTodos(): Flow<List<TodoItem>>
    suspend fun getTodoById(id: Int): TodoItem?
    suspend fun insertTodo(todo: TodoItem)
    suspend fun deleteTodo(todo: TodoItem)
    suspend fun updateTodo(todo: TodoItem)
    suspend fun uploadToFirebase(todo: TodoItem)
    suspend fun uploadImageToFirebase(imageUri: Uri?): String
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

    override suspend fun uploadToFirebase(todo: TodoItem) {
        // firebase db initialization
        val database  = FirebaseDatabase.getInstance()
            .reference
        // we target our db by name
        val newTodoRef = database.child("todos")
            .push()
        // then we insert the data to realtime db
        newTodoRef.setValue(todo)
    }

    // suspend function
    override suspend fun uploadImageToFirebase(
        imageUri: Uri?) : String {
        // storage bucket reference ; initialize
        val storageRef = FirebaseStorage.getInstance()
            .reference
        val imageRef = storageRef.child(
            "todo_images/${UUID.randomUUID()}.jpg")
        // push image to the folder above
        if (imageUri != null) {
            imageRef.putFile(imageUri).await()
        }
        return imageRef.downloadUrl.await().toString()
    }

}

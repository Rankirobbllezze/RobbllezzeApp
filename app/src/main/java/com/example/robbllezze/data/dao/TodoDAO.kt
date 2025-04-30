package com.example.robbllezze.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.robbllezze.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

//ANNOTATE THE INTERFACE AS A DAO INTERFACE FROM THE ROOM LIB.
@Dao
interface TodoDAO {
//    METHODS FOR THE CRUD operations
    // ANNOTATIONS FROM ANDROID ROOM
    @Query("SELECT * FROM todos")
    fun getAllTodos(): Flow<List<TodoItem>>

    @Query("SELECT * FROM todos WHERE id = :id")
    suspend fun getTodoById(id: Int): TodoItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoItem)

    @Update
    suspend fun updateTodo(todo: TodoItem)

    @Delete
    suspend fun deleteTodo(todo: TodoItem)


}
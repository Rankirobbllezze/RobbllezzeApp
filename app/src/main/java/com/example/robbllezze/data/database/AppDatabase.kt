package com.example.robbllezze.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.robbllezze.data.dao.TodoDAO
import com.example.robbllezze.data.model.TodoItem

//Add annotations database to make this class as the database migration layer
@Database(
    entities = [TodoItem::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    //define ana abstract function from our database interface
    abstract fun todoDao() : TodoDAO
    //add sed data....
}
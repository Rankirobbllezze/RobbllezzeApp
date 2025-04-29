package com.example.robbllezze.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//to define a data model in Andoid we annotate with the extension @Entity
//Entity is a part ROOM PERSISTENCE LIBRARY, which marks my class as a persistable entity i.e. database table
//each property defined in this class will be a column for our local sqlite table
@Entity(tableName = "todos")
//define the class as a data by adding the data keyword
data class TodoItem (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val imageUri : String,
    val tasker: String,
    val createdAt: Long = System.currentTimeMillis(), // capture time
    val isCompleted: Boolean = false

)

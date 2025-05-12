package com.example.robbllezze.data.database

import android.content.Context
import androidx.room.Room
import com.example.robbllezze.data.dao.TodoDAO
import com.example.robbllezze.data.repository.TodoRepository
import com.example.robbllezze.data.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APPModule {
    @Provides
    @Singleton
    fun provideToDoRepository(dao: TodoDAO) : TodoRepository{
        return TodoRepositoryImpl(dao)
    }
    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context):
            AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Todo_db"
        ).build()
    }
    @Provides
    fun provideTodoDao(database: AppDatabase): TodoDAO {
        return database.todoDao()
    }
}
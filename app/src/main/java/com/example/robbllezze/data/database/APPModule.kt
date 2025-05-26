package com.example.robbllezze.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.robbllezze.data.dao.TodoDAO
import com.example.robbllezze.data.repository.ApiRepository
import com.example.robbllezze.data.repository.TodoRepository
import com.example.robbllezze.data.repository.TodoRepositoryImpl
import com.example.robbllezze.data.services.TaskApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APPModule {
    //migration functionality
    private val MIGRATION_1_2 = object : Migration(1,2){
        override fun migrate(db: SupportSQLiteDatabase) {
//            super.migrate(db)
            db.execSQL(
                "ALTER TABLE todos ADD COLUMN firebase_id " + "INTEGER NOT NULL DEFAULT 0"
            )
        }

    }
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
        ).addMigrations(MIGRATION_1_2).build()
    }
    @Provides
    fun provideTodoDao(database: AppDatabase): TodoDAO {
        return database.todoDao()
    }
    //INSTANCES FOR API PROCESSES
    @Provides
    @Singleton
    fun provideApiService(): TaskApiService = TaskApiService.create()

    @Provides
    @Singleton
    fun provideApiRepository(apiService: TaskApiService) : ApiRepository = ApiRepository(apiService)
}
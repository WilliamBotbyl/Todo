package com.mobilejavaapp.todo.DI

import android.app.Application
import androidx.room.Room
import com.mobilejavaapp.todo.data.TodoDatabase
import com.mobilejavaapp.todo.data.TodoRepositoryImpl
import com.mobilejavaapp.todo.data.repository.TodoRepositoy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepositoy {
        return TodoRepositoryImpl(db.dao)
    }
}
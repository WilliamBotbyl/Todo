package com.mobilejavaapp.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobilejavaapp.todo.domain.model.Todo

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {
    abstract val dao: TodoDAO

    companion object{
        const val DATABASE_NAME = "todo_db"
    }
}
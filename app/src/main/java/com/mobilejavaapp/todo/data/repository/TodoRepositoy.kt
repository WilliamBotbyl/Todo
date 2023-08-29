package com.mobilejavaapp.todo.data.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobilejavaapp.todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepositoy {
    fun getAllTodos(): Flow<List<Todo>>

    suspend fun getTodoById(id: Int): Todo?

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)
}
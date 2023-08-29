package com.mobilejavaapp.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobilejavaapp.todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDAO {

    @Query("SELECT * from todo")
    fun getAllTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}
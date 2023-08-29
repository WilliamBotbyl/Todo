package com.mobilejavaapp.todo.data

import com.mobilejavaapp.todo.data.repository.TodoRepositoy
import com.mobilejavaapp.todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDAO
): TodoRepositoy {

    override fun getAllTodos(): Flow<List<Todo>> {
        return dao.getAllTodos()
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }
}
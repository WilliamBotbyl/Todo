package com.mobilejavaapp.todo.presentation.todo_list

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilejavaapp.todo.data.repository.TodoRepositoy
import com.mobilejavaapp.todo.domain.model.Todo
import com.mobilejavaapp.todo.presentation.util.Routes
import com.mobilejavaapp.todo.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repositoy: TodoRepositoy
) : ViewModel() {

    val todos = repositoy.getAllTodos()

    private val _uiEvent = Channel<UiEvent>() //channel
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null


    fun onEvent(event: TodoListEvent){
        when(event){
            is TodoListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }
            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repositoy.deleteTodo(event.todo)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Todo deleted",
                        action = "Undo"
                    ))
                }
            }
            is TodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repositoy.insertTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let {todo ->
                    viewModelScope.launch {
                        repositoy.insertTodo(todo)
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
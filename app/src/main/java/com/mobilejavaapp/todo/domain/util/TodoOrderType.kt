package com.mobilejavaapp.todo.domain.util

sealed class TodoOrderType {
    object Ascending: TodoOrderType()
    object Descending: TodoOrderType()
}
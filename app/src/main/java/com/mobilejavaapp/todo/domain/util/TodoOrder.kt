package com.mobilejavaapp.todo.domain.util

sealed class TodoOrder(private val orderType: TodoOrderType) {
    class Title(orderType: TodoOrderType): TodoOrder(orderType)
    class Date(orderType: TodoOrderType): TodoOrder(orderType)

    fun copy(orderType: TodoOrderType): TodoOrder {
        //returns when current NoteOrder
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
        }
    }
}
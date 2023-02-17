package com.example.motostranacompose.core

interface EventHandler<E> {
    fun obtainEvent(event: E)
}
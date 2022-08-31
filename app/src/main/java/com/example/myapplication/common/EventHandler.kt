package com.example.myapplication.common

interface EventHandler<E> {
    fun obtainEvent(event: E)
}
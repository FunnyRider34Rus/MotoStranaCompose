package com.example.myapplication.common

interface UIEvent<E> {
    fun obtainEvent(event: E)
}
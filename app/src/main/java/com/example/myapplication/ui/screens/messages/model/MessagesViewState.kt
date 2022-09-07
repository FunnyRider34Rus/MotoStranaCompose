package com.example.myapplication.ui.screens.messages.model

import com.example.myapplication.models.Message

data class MessagesViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val stateOfRegion: String = "",
    val cityOfRegion: String = "",
    val messages: List<Message> = listOf()
)

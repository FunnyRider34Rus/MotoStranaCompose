package com.example.myapplication.ui.screens.messages.model

import androidx.compose.runtime.Stable
import com.example.myapplication.common.Error
import com.example.myapplication.models.Message

@Stable
data class MessagesViewState(
    var isLoading: Boolean = false,
    var isError: Error = Error.NONE,
    var location: String = "",
    val messages: List<Message?> = listOf()
)

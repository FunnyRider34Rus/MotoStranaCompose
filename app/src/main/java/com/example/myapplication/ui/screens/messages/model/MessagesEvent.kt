package com.example.myapplication.ui.screens.messages.model

sealed class MessagesEvent {
    data class StateClicked(val state: String?): MessagesEvent()
    data class CityClicked(val city: String?): MessagesEvent()
    data class SendMessagesClicked (val text: String): MessagesEvent()
    data class SendMediaClicked (val uri: String): MessagesEvent()
}
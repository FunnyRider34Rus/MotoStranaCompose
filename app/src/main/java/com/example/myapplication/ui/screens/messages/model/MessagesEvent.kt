package com.example.myapplication.ui.screens.messages.model

sealed class MessagesEvent {
    object StateClicked: MessagesEvent()
    object CityClicked: MessagesEvent()
    data class SendMessagesClicked (val text: String): MessagesEvent()
    data class SendMediaClicked (val uri: String): MessagesEvent()
    object OnPermissionGranted : MessagesEvent()
    object OnPermissionDenied : MessagesEvent()
}
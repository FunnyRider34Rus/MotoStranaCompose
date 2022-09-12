package com.example.myapplication.ui.screens.messages.model

sealed class MessagesEvent {
    data class TabsClicked(val location: String?): MessagesEvent()
    data class SendMessagesClicked (val location: String?, val text: String, val mediaUrl: String): MessagesEvent()
    data class SendMediaClicked (val location: String?, val text: String, val mediaUrl: String): MessagesEvent()
}
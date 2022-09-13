package com.example.myapplication.ui.screens.messages.model

import android.net.Uri

sealed class MessagesEvent {
    data class TabsClicked(val location: String?): MessagesEvent()
    data class SendMessageClicked (val location: String?, val text: String): MessagesEvent()
    data class SendImageClicked (val location: String?, val mediaUrl: Uri?): MessagesEvent()
    data class FullImageMode (val mediaUrl: String?): MessagesEvent()
}
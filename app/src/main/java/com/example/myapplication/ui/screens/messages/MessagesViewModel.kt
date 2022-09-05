package com.example.myapplication.ui.screens.messages

import androidx.lifecycle.ViewModel
import com.example.myapplication.common.UIEvent
import com.example.myapplication.ui.screens.messages.model.MessagesEvent
import javax.inject.Inject

class MessagesViewModel @Inject constructor() : ViewModel(), UIEvent<MessagesEvent> {
    override fun obtainEvent(event: MessagesEvent) {
        when (event) {
                MessagesEvent.StateClicked -> { }
                MessagesEvent.CityClicked -> { }
                is MessagesEvent.SendMessagesClicked -> { }
                is MessagesEvent.SendMediaClicked -> { }
        }
    }
}
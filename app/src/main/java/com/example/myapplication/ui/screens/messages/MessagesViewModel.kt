package com.example.myapplication.ui.screens.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.common.UIEvent
import com.example.myapplication.ui.screens.dashboard.model.DashboardViewState
import com.example.myapplication.ui.screens.messages.model.MessagesEvent
import com.example.myapplication.ui.screens.messages.model.MessagesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor() : ViewModel(), UIEvent<MessagesEvent> {

    private val _viewState = MutableLiveData(MessagesViewState())
    val viewState: LiveData<MessagesViewState> = _viewState
    override fun obtainEvent(event: MessagesEvent) {
        when (event) {
            is MessagesEvent.StateClicked -> {}
            is MessagesEvent.CityClicked -> {}
            is MessagesEvent.SendMessagesClicked -> {}
            is MessagesEvent.SendMediaClicked -> {}

        }
    }
}
package com.example.myapplication.ui.screens.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.common.UIEvent
import com.example.myapplication.ui.screens.dashboard.model.DashboardViewState
import com.example.myapplication.ui.screens.messages.model.MessagesEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MessagesViewModel @Inject constructor() : ViewModel(), UIEvent<MessagesEvent> {

    private val _performLocationAction: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val performLocationAction = _performLocationAction.asStateFlow()

    fun setPerformLocationAction(request: Boolean) {
        _performLocationAction.value = request
    }

    private val _viewState = MutableLiveData(DashboardViewState())
    val viewState: LiveData<DashboardViewState> = _viewState
    override fun obtainEvent(event: MessagesEvent) {
        when (event) {
                MessagesEvent.StateClicked -> { }
                MessagesEvent.CityClicked -> { }
                MessagesEvent.OnPermissionGranted -> { }
                MessagesEvent.OnPermissionDenied -> { }
                is MessagesEvent.SendMessagesClicked -> { }
                is MessagesEvent.SendMediaClicked -> { }

        }
    }
}
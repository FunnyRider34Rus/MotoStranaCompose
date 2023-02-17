package com.example.motostranacompose.ui.authentication

import androidx.lifecycle.ViewModel
import com.example.motostranacompose.core.EventHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel(), EventHandler<AuthEvent> {

    private val _viewState = MutableStateFlow(AuthViewState())
    val viewState: StateFlow<AuthViewState> = _viewState

    override fun obtainEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.CheckBoxClick -> checkBoxStateChange()
        }
    }

    private fun checkBoxStateChange() {
        _viewState.value = _viewState.value.copy(isCheck = !_viewState.value.isCheck)
    }
}
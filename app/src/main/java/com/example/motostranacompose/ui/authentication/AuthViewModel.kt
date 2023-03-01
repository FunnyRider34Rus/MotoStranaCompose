package com.example.motostranacompose.ui.authentication

import androidx.lifecycle.ViewModel
import com.example.motostranacompose.core.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel(), EventHandler<AuthEvent> {
    private val _viewState = MutableStateFlow(AuthViewState())
    val viewState: StateFlow<AuthViewState> = _viewState
    override fun obtainEvent(event: AuthEvent) {
        when(event) {
            AuthEvent.CheckBoxClick -> checkBoxStateChange()
            AuthEvent.ButtonClick -> {  }
        }
    }

    private fun checkBoxStateChange() {
        _viewState.value = _viewState.value.copy(isCheck = !_viewState.value.isCheck)
    }
}
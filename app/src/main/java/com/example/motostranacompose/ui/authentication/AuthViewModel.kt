package com.example.motostranacompose.ui.authentication

import androidx.lifecycle.ViewModel
import com.example.motostranacompose.core.AccountService
import com.example.motostranacompose.core.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val accountService: AccountService) : ViewModel(), EventHandler<AuthEvent> {

    private val _viewState = MutableStateFlow(AuthViewState())
    val viewState: StateFlow<AuthViewState> = _viewState

    init {
        _viewState.value = _viewState.value.copy(isUserAuth = accountService.hasUser)
    }

    override fun obtainEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.CheckBoxClick -> checkBoxStateChange()
            AuthEvent.AuthButtonClick -> isUserAuthorized()
        }
    }

    private fun checkBoxStateChange() {
        _viewState.value = _viewState.value.copy(isCheck = !_viewState.value.isCheck)
    }

    private fun isUserAuthorized() {
        _viewState.value = _viewState.value.copy(isUserAuth = !_viewState.value.isUserAuth)
    }
}
package com.example.motostranacompose.ui.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motostranacompose.core.EventHandler
import com.example.motostranacompose.core.Response.Success
import com.example.motostranacompose.domain.repository.AuthRepository
import com.example.motostranacompose.domain.repository.OneTapSignInResponse
import com.example.motostranacompose.domain.repository.SignInWithGoogleResponse
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    val oneTapClient: SignInClient
) : ViewModel(), EventHandler<AuthEvent> {

    private val _viewState = MutableStateFlow(AuthViewState())
    val viewState: StateFlow<AuthViewState> = _viewState

    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Success(null))
        private set
    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(Success(false))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        _viewState.value = _viewState.value.copy(isLoading = true)
        oneTapSignInResponse = repository.oneTapSignInWithGoogle()
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        _viewState.value = _viewState.value.copy(isLoading = true)
        signInWithGoogleResponse = repository.firebaseSignInWithGoogle(googleCredential)
    }

    override fun obtainEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.CheckBoxClick -> checkBoxStateChange()
            AuthEvent.ButtonClick -> oneTapSignIn()
        }
    }

    private fun checkBoxStateChange() {
        _viewState.value = _viewState.value.copy(isCheck = !_viewState.value.isCheck)
    }
}
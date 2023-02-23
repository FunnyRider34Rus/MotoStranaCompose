package com.example.motostranacompose.ui.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motostranacompose.core.EventHandler
import com.example.motostranacompose.data.model.Response.Loading
import com.example.motostranacompose.data.model.Response.Success
import com.example.motostranacompose.data.repository.AuthRepository
import com.example.motostranacompose.data.repository.OneTapSignInResponse
import com.example.motostranacompose.data.repository.SignInWithGoogleResponse
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


    override fun obtainEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.CheckBoxClick -> checkBoxStateChange()
            AuthEvent.AuthButtonClick -> oneTapSignIn()
        }
    }

    private fun checkBoxStateChange() {
        _viewState.value = _viewState.value.copy(isCheck = !_viewState.value.isCheck)
    }

    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Success(null))
        private set
    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(Success(false))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResponse = Loading
        oneTapSignInResponse = repository.oneTapSignInWithGoogle()
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        oneTapSignInResponse = Loading
        signInWithGoogleResponse = repository.firebaseSignInWithGoogle(googleCredential)
    }
}
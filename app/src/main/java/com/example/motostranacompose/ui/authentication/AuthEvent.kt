package com.example.motostranacompose.ui.authentication

sealed class AuthEvent {
    object AuthButtonClick : AuthEvent()
    object CheckBoxClick : AuthEvent()
}

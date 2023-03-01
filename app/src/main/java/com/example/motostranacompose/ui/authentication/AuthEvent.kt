package com.example.motostranacompose.ui.authentication

sealed class AuthEvent {
    object CheckBoxClick : AuthEvent()
    object ButtonClick : AuthEvent()
}
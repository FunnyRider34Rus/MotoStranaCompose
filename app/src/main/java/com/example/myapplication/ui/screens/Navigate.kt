package com.example.myapplication.ui.screens

sealed class Navigate (val route: String) {
    object Welcome : Navigate("welcome")
    object InputPhoneNumber : Navigate("input_phone_number")
    object InputSMSCode : Navigate("input_sms_code")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

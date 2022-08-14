package com.example.myapplication.ui.screens.registration.navigation

import androidx.annotation.StringRes
import com.example.myapplication.R

sealed class Screen (val route: String, @StringRes val resourceId: Int) {
    object Welcome : Screen("welcome", R.string.welcome_title_text)
    object InputPhoneNumber : Screen("input_phone_number", R.string.input_phone_number_title_text)
    object InputSMSCode : Screen("input_sms_code", R.string.input_sms_code_title_text)
    object InputUserInfo: Screen("input_user_info", R.string.input_user_info_title)
    object MainScreen: Screen("mainscreen", R.string.main_screen)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

package com.example.myapplication.ui.navigation

import androidx.annotation.StringRes
import com.example.myapplication.ACTIVITY_CONTEXT
import com.example.myapplication.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Dashboard : BottomBarScreen(
        route = "dashboard",
        title = ACTIVITY_CONTEXT.getString(R.string.dashboard),
        icon = R.drawable.ic_menu_dashboard
    )
    object Messages : BottomBarScreen(
        route = "messages",
        title = ACTIVITY_CONTEXT.getString(R.string.messages),
        icon = R.drawable.ic_menu_message
    )
    object Settings : BottomBarScreen(
        route = "settings",
        title = ACTIVITY_CONTEXT.getString(R.string.settings),
        icon = R.drawable.ic_menu_settings
    )
}

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

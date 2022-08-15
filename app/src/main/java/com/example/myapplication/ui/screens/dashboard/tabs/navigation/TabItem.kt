package com.example.myapplication.ui.screens.dashboard.tabs.navigation

import androidx.compose.runtime.Composable
import com.example.myapplication.ACTIVITY_CONTEXT
import com.example.myapplication.R

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(
    var title: String,
    var screen: ComposableFun
) {
    object News : TabItem(ACTIVITY_CONTEXT.getString(R.string.news), { News })
    object Events : TabItem(ACTIVITY_CONTEXT.getString(R.string.events), { Events })
}

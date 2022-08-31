package com.example.myapplication.ui.screens.dashboard.model

import androidx.compose.runtime.Stable
import com.example.myapplication.models.Event

@Stable
data class DashboardViewState(
    val dashboardValue : List<Event?> = listOf(),
    val itemValue: Event? = null
)

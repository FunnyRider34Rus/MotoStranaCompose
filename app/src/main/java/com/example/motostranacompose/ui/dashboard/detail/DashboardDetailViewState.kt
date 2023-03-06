package com.example.motostranacompose.ui.dashboard.detail

import com.example.motostranacompose.data.model.DashboardContent

data class DashboardDetailViewState(
    val content: DashboardContent? = null,
    val isLoading: Boolean = false
)
package com.example.motostranacompose.ui.dashboard.list

import com.example.motostranacompose.ui.dashboard.model.DashboardContent

data class DashboardListViewState (
    val content: DashboardContent? = null,
    val likeState: LikeState = LikeState.NONE,
    val commentState: Boolean = false
)

enum class LikeState {
    NONE, UNLIKE, LIKE
}
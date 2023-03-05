package com.example.motostranacompose.ui.dashboard.list

sealed class DashboardListEvent {
    object ContentClick : DashboardListEvent()
    object LikeClick : DashboardListEvent()
    object CommentClick : DashboardListEvent()
    object ShareClick : DashboardListEvent()

    object ButtonAddClick : DashboardListEvent()
}
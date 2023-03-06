package com.example.motostranacompose.ui.dashboard.list

import com.example.motostranacompose.data.model.DashboardContent

interface DashboardListEvent {
    object ContentClick : DashboardListEvent
    class LikeClick(val content: DashboardContent) : DashboardListEvent
    object CommentClick : DashboardListEvent
    object ShareClick : DashboardListEvent

    object ButtonAddClick : DashboardListEvent
}
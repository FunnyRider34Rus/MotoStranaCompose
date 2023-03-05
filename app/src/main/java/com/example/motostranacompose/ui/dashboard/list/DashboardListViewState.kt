package com.example.motostranacompose.ui.dashboard.list

import com.example.motostranacompose.data.model.DashboardContent

data class DashboardListViewState(
    val likeStatus: LikeStatus? = null,
    val commentStatus: CommentStatus? = null,
    val isLoading: Boolean = false,
    val error: String = "",
    val contents: List<DashboardContent> = emptyList(),
    val content: DashboardContent? = null
)

enum class LikeStatus {
    NONE, UNLIKE, LIKE
}

enum class CommentStatus {
    NONE, HAS
}
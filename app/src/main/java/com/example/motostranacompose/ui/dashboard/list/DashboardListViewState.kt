package com.example.motostranacompose.ui.dashboard.list

import com.example.motostranacompose.data.model.DashboardContent
import kotlinx.coroutines.flow.Flow

class DashboardListViewState {
    var content: Flow<List<DashboardContent>?>? = null
    val isLike: LikeStatus = LikeStatus.NONE
    val isCommnet: Boolean = false
}

enum class LikeStatus {
    NONE, UNLIKE, LIKE
}
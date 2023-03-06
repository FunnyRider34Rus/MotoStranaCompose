package com.example.motostranacompose.domain.use_case.dashboard

data class DashboardUseCases(
    val getAll: GetDashboardContents,
    val getByKey: GetDashboardContentByKey,
    val insert: InsertDashboardContent,
    val likeClick: DashboardLikeClickContent,
    val delete: DeleteDashboardContent
)
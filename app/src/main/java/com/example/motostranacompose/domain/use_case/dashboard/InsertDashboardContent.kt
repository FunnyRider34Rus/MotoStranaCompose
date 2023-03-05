package com.example.motostranacompose.domain.use_case.dashboard

import com.example.motostranacompose.data.model.DashboardContent
import com.example.motostranacompose.domain.repository.DashboardContentRepository

class InsertDashboardContent(private val dashboardContentRepository: DashboardContentRepository) {
    suspend operator fun invoke(dashboardContent: DashboardContent) =
        dashboardContentRepository.insert(data = dashboardContent)
}
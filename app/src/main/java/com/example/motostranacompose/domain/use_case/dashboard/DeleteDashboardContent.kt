package com.example.motostranacompose.domain.use_case.dashboard

import com.example.motostranacompose.domain.repository.DashboardContentRepository

class DeleteDashboardContent(private val dashboardContentRepository: DashboardContentRepository) {
    suspend operator fun invoke(key: String?) = dashboardContentRepository.delete(key = key)
}
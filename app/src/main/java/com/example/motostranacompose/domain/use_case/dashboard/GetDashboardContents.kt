package com.example.motostranacompose.domain.use_case.dashboard

import com.example.motostranacompose.domain.repository.DashboardContentRepository

class GetDashboardContents(private val dashboardContentRepository: DashboardContentRepository) {
    suspend operator fun invoke() = dashboardContentRepository.getAll()
}
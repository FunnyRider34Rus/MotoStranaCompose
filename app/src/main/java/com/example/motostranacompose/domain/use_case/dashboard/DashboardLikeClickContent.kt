package com.example.motostranacompose.domain.use_case.dashboard

import com.example.motostranacompose.core.addOrRemoveIfContains
import com.example.motostranacompose.data.model.DashboardContent
import com.example.motostranacompose.domain.repository.DashboardContentRepository
import com.example.motostranacompose.domain.repository.UserRepository

class DashboardLikeClickContent(private val dashboardContentRepository: DashboardContentRepository, private val userRepository: UserRepository) {
    suspend fun invoke(dashboardContent: DashboardContent) {
        val currentUser = userRepository.getCurrentUser()
        val mData = dashboardContent.copy(likes = addOrRemoveIfContains(dashboardContent.likes, currentUser.uid.toString()))
        dashboardContentRepository.update(data = mData)
    }
}
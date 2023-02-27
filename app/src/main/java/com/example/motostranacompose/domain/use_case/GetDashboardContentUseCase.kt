package com.example.motostranacompose.domain.use_case

import com.example.motostranacompose.data.model.DashboardContent
import com.example.motostranacompose.data.repository.DashboardNewsRepository
import com.example.motostranacompose.data.repository.DashboardPostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class GetDashboardContentUseCase @Inject constructor(
    private val newsRepository: DashboardNewsRepository,
    private val postsRepository: DashboardPostsRepository
) {
    suspend fun invoke(): Flow<List<DashboardContent>?> {
        val news = newsRepository.get()
        val posts = postsRepository.get()
        val result = merge(news, posts).filter { !it.isNullOrEmpty() }
        result.map { flow -> flow?.sortedBy { sort -> sort.timestamp } }
        return result
    }
}
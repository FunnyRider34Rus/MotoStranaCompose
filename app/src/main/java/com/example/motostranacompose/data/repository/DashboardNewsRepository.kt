package com.example.motostranacompose.data.repository

import com.example.motostranacompose.data.model.DashboardContent.News
import kotlinx.coroutines.flow.Flow

interface DashboardNewsRepository {
    suspend fun get(): Flow<List<News>?>

    suspend fun insert(news: News)

    suspend fun update(news: News)

    suspend fun delete(news: News)
}
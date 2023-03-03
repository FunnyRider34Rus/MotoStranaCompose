package com.example.motostranacompose.domain.repository

import com.example.motostranacompose.core.Response
import com.example.motostranacompose.data.model.DashboardContent
import kotlinx.coroutines.flow.Flow

interface DashboardContentRepository {
    suspend fun getAll(): Flow<Response<List<DashboardContent>>>
    suspend fun getByKet(key: String?): Flow<Response<DashboardContent>>
    suspend fun insert(data: DashboardContent): Response<Boolean>
    suspend fun update(data: DashboardContent): Response<Boolean>
    suspend fun delete(key: String?): Response<Boolean>
}
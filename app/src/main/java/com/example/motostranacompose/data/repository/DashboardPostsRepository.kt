package com.example.motostranacompose.data.repository

import com.example.motostranacompose.data.model.DashboardContent.Post
import kotlinx.coroutines.flow.Flow

interface DashboardPostsRepository {
    suspend fun get(): Flow<List<Post>?>

    suspend fun insert(post: Post)

    suspend fun update(post: Post)

    suspend fun delete(post: Post)
}
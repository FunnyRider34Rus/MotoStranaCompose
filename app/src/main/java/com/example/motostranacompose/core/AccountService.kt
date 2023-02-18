package com.example.motostranacompose.core

import com.example.motostranacompose.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>
    suspend fun signOut()
}
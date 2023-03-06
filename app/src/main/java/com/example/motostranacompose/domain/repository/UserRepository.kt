package com.example.motostranacompose.domain.repository

import com.example.motostranacompose.data.model.CurrentUser
import com.example.motostranacompose.data.model.User

interface UserRepository {

    fun getCurrentUser(): CurrentUser

    fun getUserByUid(): User
}
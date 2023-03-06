package com.example.motostranacompose.data.repository

import com.example.motostranacompose.data.model.CurrentUser
import com.example.motostranacompose.data.model.User
import com.example.motostranacompose.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class UserRepositoryImpl : UserRepository {
    override fun getCurrentUser(): CurrentUser {
        val auth = FirebaseAuth.getInstance().currentUser
        return CurrentUser(
            uid = auth?.uid,
            displayName = auth?.displayName,
            photoURL = auth?.photoUrl.toString()
        )
    }

    override fun getUserByUid(): User {
        TODO("Not yet implemented")
    }
}
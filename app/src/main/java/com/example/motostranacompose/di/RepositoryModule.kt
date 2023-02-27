package com.example.motostranacompose.di

import com.example.motostranacompose.core.Constants.SIGN_IN_REQUEST
import com.example.motostranacompose.core.Constants.SIGN_UP_REQUEST
import com.example.motostranacompose.data.repository.*
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(dashboardNewsRepositoryImpl: DashboardNewsRepositoryImpl): DashboardNewsRepository

    @Binds
    abstract fun bindPostsRepository(dashboardPostsRepositoryImpl: DashboardPostsRepositoryImpl): DashboardPostsRepository
}

@Module
@InstallIn(ViewModelComponent::class)
class AuthRepositoryModule {
    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        @Named(SIGN_IN_REQUEST)
        signInRequest: BeginSignInRequest,
        @Named(SIGN_UP_REQUEST)
        signUpRequest: BeginSignInRequest,
        signInClient: GoogleSignInClient,
        firestore: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(
        auth = auth,
        oneTapClient = oneTapClient,
        signInRequest = signInRequest,
        signUpRequest = signUpRequest,
        signInClient = signInClient,
        firestore = firestore
    )
}
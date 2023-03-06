package com.example.motostranacompose.di

import com.example.motostranacompose.core.Constants.FIRESTORE_NODE_DASHBOARD
import com.example.motostranacompose.data.repository.DashboardRepositoryImpl
import com.example.motostranacompose.data.repository.UserRepositoryImpl
import com.example.motostranacompose.domain.repository.DashboardContentRepository
import com.example.motostranacompose.domain.repository.UserRepository
import com.example.motostranacompose.domain.use_case.dashboard.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingletonsComponent {
    @Provides
    fun provideUserRepository(): UserRepository = UserRepositoryImpl()
    @Provides
    fun provideDashboardConetentRef() = Firebase.firestore.collection(FIRESTORE_NODE_DASHBOARD)

    @Provides
    fun provideDashoardContentRepository(contentRef: CollectionReference): DashboardContentRepository =
        DashboardRepositoryImpl(contentRef)

    @Provides
    fun provideDashboardUseCases(dashboardContentRepository: DashboardContentRepository, userRepository: UserRepository) =
        DashboardUseCases(
            getAll = GetDashboardContents(dashboardContentRepository),
            getByKey = GetDashboardContentByKey(dashboardContentRepository),
            insert = InsertDashboardContent(dashboardContentRepository),
            likeClick = DashboardLikeClickContent(dashboardContentRepository, userRepository),
            delete = DeleteDashboardContent(dashboardContentRepository)
        )


}
package com.example.motostranacompose.di

import com.example.motostranacompose.core.Constants.FIRESTORE_NODE_DASHBOARD
import com.example.motostranacompose.data.repository.DashboardRepositoryImpl
import com.example.motostranacompose.domain.repository.DashboardContentRepository
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
    fun provideDashboardConetentRef() = Firebase.firestore.collection(FIRESTORE_NODE_DASHBOARD)

    @Provides
    fun provideDashoardContentRepository(contentRef: CollectionReference): DashboardContentRepository =
        DashboardRepositoryImpl(contentRef)

    @Provides
    fun provideDashboardUseCases(dashboardContentRepository: DashboardContentRepository) =
        DashboardUseCases(
            getAll = GetDashboardContents(dashboardContentRepository),
            getByKey = GetDashboardContentByKey(dashboardContentRepository),
            insert = InsertDashboardContent(dashboardContentRepository),
            update = UpdateDashboardContent(dashboardContentRepository),
            delete = DeleteDashboardContent(dashboardContentRepository)
        )
}
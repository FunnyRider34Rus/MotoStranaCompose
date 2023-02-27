package com.example.motostranacompose.di

import com.example.motostranacompose.data.repository.DashboardNewsRepository
import com.example.motostranacompose.data.repository.DashboardPostsRepository
import com.example.motostranacompose.domain.use_case.GetDashboardContentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetDashboardUseCase(newsRepository: DashboardNewsRepository, postsRepository: DashboardPostsRepository): GetDashboardContentUseCase {
        return GetDashboardContentUseCase(newsRepository, postsRepository)
    }
}
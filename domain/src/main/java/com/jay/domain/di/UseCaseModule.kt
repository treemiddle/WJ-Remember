package com.jay.domain.di

import com.jay.domain.repository.GithubRepository
import com.jay.domain.usecase.GithubUseCase
import com.jay.domain.usecase.LocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGithubUseCase(
        githubRepository: GithubRepository
    ) : GithubUseCase {
        return GithubUseCase(githubRepository)
    }

    @Provides
    @Singleton
    fun provideLocalUseCase(
        githubRepository: GithubRepository
    ): LocalUseCase {
        return LocalUseCase(githubRepository)
    }

}
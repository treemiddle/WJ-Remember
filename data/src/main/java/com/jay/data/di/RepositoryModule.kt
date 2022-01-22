package com.jay.data.di

import com.jay.data.GithubRepositoryImpl
import com.jay.data.LocalRepositoryImpl
import com.jay.data.local.UserLocalDataSource
import com.jay.data.remote.GithubRemoteDataSource
import com.jay.domain.repository.GithubRepository
import com.jay.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(
        githubRemoteDataSource: GithubRemoteDataSource,
        userLocalDataSource: UserLocalDataSource
    ): GithubRepository {
        return GithubRepositoryImpl(
            githubRemoteDataSource,
            userLocalDataSource
        )
    }

    @Provides
    @Singleton
    fun provideLocalRepository(
        userLocalDataSource: UserLocalDataSource
    ) : LocalRepository {
        return LocalRepositoryImpl(userLocalDataSource)
    }

}
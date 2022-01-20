package com.jay.data.di

import com.jay.data.GithubRepositoryImpl
import com.jay.data.remote.GithubRemoteDataSource
import com.jay.domain.repository.GithubRepository
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
        githubRemoteDataSource: GithubRemoteDataSource
    ) : GithubRepository {
        return GithubRepositoryImpl(githubRemoteDataSource)
    }

}
package com.jay.remote.di

import com.jay.data.remote.GithubRemoteDataSource
import com.jay.remote.GithubRemoteDataSourceImpl
import com.jay.remote.api.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {

    @Provides
    @Singleton
    fun provideGithubRemoteDataSource(githubApi: GithubApi): GithubRemoteDataSource {
        return GithubRemoteDataSourceImpl(githubApi)
    }

}
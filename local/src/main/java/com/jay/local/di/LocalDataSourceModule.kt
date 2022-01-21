package com.jay.local.di

import com.jay.data.local.GithubLocalDataSource
import com.jay.local.GithubLocalDataSourceImpl
import com.jay.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideGithubLocalDataSource(
        userDao: UserDao
    ): GithubLocalDataSource {
        return GithubLocalDataSourceImpl(userDao)
    }

}
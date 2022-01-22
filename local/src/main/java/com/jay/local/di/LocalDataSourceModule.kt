package com.jay.local.di

import com.jay.data.local.UserLocalDataSource
import com.jay.local.UserLocalDataSourceImpl
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
    ): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDao)
    }

}
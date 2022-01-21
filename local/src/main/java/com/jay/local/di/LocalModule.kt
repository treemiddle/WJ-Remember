package com.jay.local.di

import android.content.Context
import androidx.room.Room
import com.jay.local.dao.UserDao
import com.jay.local.database.WJDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val DATABASE_NAME = "wj_database"

    @Provides
    @Singleton
    fun provideWJDatabase(
        @ApplicationContext applicationContext: Context
    ): WJDatabase {
        return Room.databaseBuilder(
            applicationContext,
            WJDatabase::class.java,
            DATABASE_NAME
        )
            .build()
    }

    @Provides
    fun provideUserDao(database: WJDatabase): UserDao {
        return database.userDao()
    }

}
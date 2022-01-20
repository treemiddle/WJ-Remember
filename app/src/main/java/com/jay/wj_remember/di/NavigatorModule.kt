package com.jay.wj_remember.di

import com.jay.wj_remember.ui.main.navigator.AppNavigator
import com.jay.wj_remember.ui.main.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigatorModule {

    @Binds
    abstract fun bindMainNavigator(impl: AppNavigatorImpl): AppNavigator

}
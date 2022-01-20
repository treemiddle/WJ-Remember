package com.jay.wj_remember.ui.main.navigator

import com.jay.wj_remember.utils.FragmentType

interface AppNavigator {

    fun setMain()

    fun navigateTo(screen: FragmentType)

}